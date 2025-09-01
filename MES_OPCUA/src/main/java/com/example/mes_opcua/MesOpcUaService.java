package com.example.mes_opcua;

import com.example.mes_opcua.kafka.KafkaSender;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.milo.opcua.sdk.client.OpcUaClient;
import org.eclipse.milo.opcua.stack.core.Identifiers;
import org.eclipse.milo.opcua.stack.core.types.builtin.DataValue;
import org.eclipse.milo.opcua.stack.core.types.builtin.NodeId;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseDirection;
import org.eclipse.milo.opcua.stack.core.types.enumerated.BrowseResultMask;
import org.eclipse.milo.opcua.stack.core.types.enumerated.NodeClass;
import org.eclipse.milo.opcua.stack.core.types.enumerated.TimestampsToReturn;
import org.eclipse.milo.opcua.stack.core.types.structured.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.eclipse.milo.opcua.stack.core.types.builtin.*;
import org.example.RfidScanEvent;

import java.util.ArrayList;
import java.util.List;

import static org.eclipse.milo.opcua.stack.core.types.builtin.unsigned.Unsigned.uint;

@Service
@Slf4j
public class MesOpcUaService {
    private final OpcUaClient client;
    private boolean flag = true;

    @Autowired
    private KafkaSender kafkaSender;

    @Autowired
    public MesOpcUaService(OpcUaClient client) {
        this.client = client;
    }

    // @Scheduled(fixedRate = 1000)
    public void checkConnect() throws Exception {
        String epc = "EPC1";
        String location=getNewLocation(epc);
        RfidScanEvent deliveryLocationResponse=new RfidScanEvent();
        deliveryLocationResponse.setLocation(location);
        deliveryLocationResponse.setEpc(epc);
        deliveryLocationResponse.setReaderId("das");
        kafkaSender.sendMessage(deliveryLocationResponse,"test");
    }

    public List<AccessEmployees> getListEmployees() {
        List<AccessEmployees> accessEmployees = new ArrayList<>();
        NodeId accessNode = new NodeId(3, "Access");
        try {
            BrowseDescription brows = new BrowseDescription(accessNode,
                    BrowseDirection.Forward,
                    Identifiers.References,
                    true,
                    uint(NodeClass.Variable.getValue()),
                    uint(BrowseResultMask.All.getValue()));
            BrowseResult browseResult = client.browse(brows).get();
            for (ReferenceDescription reference : browseResult.getReferences()) {
                AccessEmployees accessEmployee = new AccessEmployees();
                NodeId nodeId = new NodeId(3, reference.getNodeId().getIdentifier().toString());
                String UIDname = reference.getNodeId().getIdentifier().toString();
                accessEmployee.setUID(UIDname.substring(UIDname.lastIndexOf("/") + 1));
                DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId).get();
                accessEmployee.setName(dataValue.getValue().getValue().toString());
//                System.out.println(reference.getDisplayName()+"  "+reference.getNodeId().getIdentifier().toString());
                BrowseDescription brows2 = new BrowseDescription(nodeId,
                        BrowseDirection.Forward,
                        Identifiers.References,
                        true,
                        uint(NodeClass.Variable.getValue()),
                        uint(BrowseResultMask.All.getValue()));
                BrowseResult browseResult2 = client.browse(brows2).get();
                for (ReferenceDescription ref :
                        browseResult2.getReferences()) {
                    AccessMachine accessMachine = new AccessMachine();
                    NodeId node = new NodeId(3, ref.getNodeId().getIdentifier().toString());
                    dataValue = client.readValue(0, TimestampsToReturn.Both, node).get();
                    String name = ref.getNodeId().getIdentifier().toString();
                    accessMachine.setNameMachine(name.substring(name.lastIndexOf("/") + 1));
                    accessMachine.setAccess((boolean) dataValue.getValue().getValue());
//                    System.out.println(ref.getDisplayName());
                    accessEmployee.addList(accessMachine);
                }
                accessEmployees.add(accessEmployee);
            }
        } catch (Exception e) {
            System.out.println("error:" + e.getMessage());
        }

        return accessEmployees;
    }

    public boolean checkAccess(String machine, String UID, OpcUaClient client) {
        NodeId accessNode = new NodeId(3, "Access/" + UID + "/" + machine);
        try {
            DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, accessNode).get();
            if (dataValue.getValue().getValue() instanceof Boolean) {
                boolean access = (boolean) dataValue.getValue().getValue();
                if (access) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
        return false;
    }

    public void changeAccess(String machine, boolean newAccess, String UID) {
        NodeId nodeId = new NodeId(3, "Access/" + UID + "/" + machine);
        try {
            Variant variant = new Variant(newAccess);
            DataValue dataValue = new DataValue(variant);
            client.writeValue(nodeId, dataValue);
        } catch (Exception e) {
            System.out.println(e.getMessage() + " ошибка в изменение" + "\n" + e.getStackTrace());
        }


    }

    public String getNewLocation(String epc) {
        NodeId nodeId = new NodeId(3, "RFID/" + epc);
        try {
            DataValue dataValue = client.readValue(0, TimestampsToReturn.Both, nodeId).get();
            return dataValue.getValue().getValue().toString();
        } catch (Exception e) {
            System.out.println("Не удачные доступ");
        }
        return "";

    }
}
//    NodeId accessNode = new NodeId(3, "Access/"+UID+"/"+machine);
//        try {
//                BrowseDescription brows = new BrowseDescription(accessNode,
//                BrowseDirection.Forward,
//                Identifiers.References,
//                true,
//                uint(NodeClass.Variable.getValue()),
//                uint(BrowseResultMask.All.getValue()));
//                BrowseResult browseResult = client.browse(brows).get();
//                for (ReferenceDescription reference : browseResult.getReferences()) {
//                if (reference.getNodeId().getIdentifier().toString().equalsIgnoreCase(machine + "/" + UID)) {
//                return true;
//                }
//                }
//                } catch (Exception e) {
//                System.out.println(e.getMessage());
//                return false;
//                }