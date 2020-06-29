import enums.ActionType;
import exception.AlreadyExistException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args)  throws java.lang.Exception{
        RbacSystem rbacSystem = new RbacSystem();
        BufferedReader bufferReader = null;
        String input = null;
        BufferedReader subBufferReader = null;
        String subInput;
        try{
            System.out.println("\n\n\n\n\n");
            System.out.println("===========================================================================");
            System.out.println("===================      Role Based Access Control     ====================");
            System.out.println("===========================================================================");
            printUsage();
            System.out.println("Please Enter 'exit' to end Execution");
            System.out.println("Input:");

            while(true){
                try {
                    bufferReader = new BufferedReader(new InputStreamReader(System.in));
                    input = bufferReader.readLine().trim();
                    if (input.equalsIgnoreCase("exit")) {
                        break;
                    } else {
                        String[] inputs = input.split(" ");
                        String key = inputs[0];
                        switch (key) {
                            case "create_user":
                                System.out.println("created user: " + rbacSystem.createUser(inputs[1], inputs[2]));
                                break;
                            case "create_resource":
                                System.out.println("created resource: " + rbacSystem.createResource(inputs[1], inputs[2]));
                                break;
                            case "create_role":
                                System.out.println("Enter no of resources:");
                                subBufferReader = new BufferedReader(new InputStreamReader(System.in));
                                subInput = bufferReader.readLine().trim();
                                String[] subInputs = subInput.split(" ");
                                int noOfResources = Integer.parseInt(subInputs[0]);
                                HashMap<String, ArrayList<ActionType>> resourceActionsMap = new HashMap<>();
                                ArrayList<String> sactionTypes = new ArrayList<>();
                                for(ActionType actionType : ActionType.values()) sactionTypes.add(actionType.name());
                                for (int i = 0; i < noOfResources; i++) {
                                    System.out.println("Enter resource identity:");
                                    subBufferReader = new BufferedReader(new InputStreamReader(System.in));
                                    subInput = bufferReader.readLine().trim();
                                    subInputs = subInput.split(" ");
                                    String resourceIdentity = subInputs[0];
                                    if(resourceActionsMap.containsKey(resourceIdentity)) throw new AlreadyExistException("Resource", resourceIdentity);
                                    System.out.println("Enter space separated action types[" +
                                            String.join("/", sactionTypes) + "]:");
                                    subBufferReader = new BufferedReader(new InputStreamReader(System.in));
                                    subInput = bufferReader.readLine().trim();
                                    subInputs = subInput.split(" ");
                                    ArrayList<ActionType> actionTypes = new ArrayList<>();
                                    for(String actionType: subInputs){
                                        actionTypes.add(ActionType.valueOf(actionType));
                                    }
                                    resourceActionsMap.put(resourceIdentity, actionTypes);
                                }
                                System.out.println("created role: " + rbacSystem.createRole(inputs[1], inputs[2], resourceActionsMap));
                                break;
                            case "add_role_to_user":
                                System.out.println("user after addition: " + rbacSystem.addRoleToUser(inputs[1], inputs[2]));
                                break;
                            case "remove_user_from_role":
                                System.out.println("user after removal: " + rbacSystem.removeUserFromRole(inputs[1], inputs[2]));
                                break;
                            case "check_user_access":
                                System.out.println("user access: " + rbacSystem.checkUserAccess(inputs[1], inputs[2], ActionType.valueOf(inputs[3])));
                                break;
                            default:
                                System.out.println("Invalid input. Please try again");
                                break;
                        }
                    }
                }catch (Exception e){
                    System.out.println("Error occurred: " + e.getMessage());
                    System.out.println("Please try again.");
                }
            }
        }finally
        {
            try {
                if (bufferReader != null)
                    bufferReader.close();
                if(subBufferReader != null)
                    subBufferReader.close();
            }
            catch (Exception e){}
        }
    }

    private static void printUsage(){
        StringBuffer buffer = new StringBuffer();
        buffer = buffer.append(
                "--------------Please Enter one of the below commands. {variable} to be replaced -----------------------")
                .append("\n");
        buffer = buffer.append("A) For creating user               ---> create_user {identity} {name}")
                .append("\n");
        buffer = buffer.append("B) For creating resource           ---> create_resource {identity} {name}")
                .append("\n");
        buffer = buffer.append("C) For creating role               ---> create_role {identity} {name}")
                .append("\n");
        buffer = buffer.append("D) For adding role to user         ---> add_role_to_user {userIdentity} {roleIdentity}")
                .append("\n");
        buffer = buffer.append("E) For removing user from role     ---> remove_user_from_role {userIdentity} {roleIdentity}")
                .append("\n");
        buffer = buffer.append("F) Check user access               ---> check_user_access {userIdentity} {resourceIdentity} {actionType}")
                .append("\n");
        System.out.println(buffer.toString());
    }
}
