**Role Based Access Control System**

**Project Requirements:**
    JDK 1.8.
    Maven 3.

**Run the application in interactive mode using maven:**
     mvn clean install
     mvn exec:java -Dexec.mainClass="Main"
     
**Important Files:**
    models(src/main/java/model/): User, Resource, Role, RolePermission
    enums(src/main/java/enums/): ActionType
    src/main/java/RbacSystem: Facade for system
    src/main/java/Main: driver main class
    
**Sample Interactions:**

===========================================================================
===================      Role Based Access Control     ====================
===========================================================================
--------------Please Enter one of the below commands. {variable} to be replaced -----------------------
A) For creating user               ---> create_user {identity} {name}
B) For creating resource           ---> create_resource {identity} {name}
C) For creating role               ---> create_role {identity} {name}
D) For adding role to user         ---> add_role_to_user {userIdentity} {roleIdentity}
E) For removing user from role     ---> remove_user_from_role {userIdentity} {roleIdentity}
F) Check user access               ---> check_user_access {userIdentity} {resourceIdentity} {actionType}

Please Enter 'exit' to end Execution
Input:
create_user u1 anu
created user: User(identity=u1, name=anu, roles={})
create_user u2 raj
created user: User(identity=u2, name=raj, roles={})
create_user u1 foo
Error occurred: User with identity u1 already exists!
Please try again.
create_resource f1 file1
created resource: Resource(identity=f1, name=file1)
create_resource f2 file2
created resource: Resource(identity=f2, name=file2)
create_role r1 admin
Enter no of resources:
2
Enter resource identity:
f1
Enter space separated action types[READ/WRITE/DELETE]:
READ DELETE
Enter resource identity:
f2
Enter space separated action types[READ/WRITE/DELETE]:
READ
created role: Role(identity=r1, name=admin, rolePermissions=[RolePermission(actionType=ActionType.READ(type=0), resource=Resource(identity=f1, name=file1)), RolePermission(actionType=ActionType.DELETE(type=2), resource=Resource(identity=f1, name=file1)), RolePermission(actionType=ActionType.READ(type=0), resource=Resource(identity=f2, name=file2))])
check_user_access u1 f1 READ
user access: false
add_role_to_user u1 r1
user after addition: User(identity=u1, name=anu, roles={r1=Role(identity=r1, name=admin, rolePermissions=[RolePermission(actionType=ActionType.READ(type=0), resource=Resource(identity=f1, name=file1)), RolePermission(actionType=ActionType.DELETE(type=2), resource=Resource(identity=f1, name=file1)), RolePermission(actionType=ActionType.READ(type=0), resource=Resource(identity=f2, name=file2))])})
check_user_access u1 f1 READ
user access: true
check_user_access u1 f1 WRITE
user access: false
check_user_access u1 f2 READ
user access: true
create_role r2 write_f1
Enter no of resources:
1
Enter resource identity:
f1
Enter space separated action types[READ/WRITE/DELETE]:
WRITE
created role: Role(identity=r2, name=write_f1, rolePermissions=[RolePermission(actionType=ActionType.WRITE(type=1), resource=Resource(identity=f1, name=file1))])
add_role_to_user u1 r2
user after addition: User(identity=u1, name=anu, roles={r2=Role(identity=r2, name=write_f1, rolePermissions=[RolePermission(actionType=ActionType.WRITE(type=1), resource=Resource(identity=f1, name=file1))]), r1=Role(identity=r1, name=admin, rolePermissions=[RolePermission(actionType=ActionType.READ(type=0), resource=Resource(identity=f1, name=file1)), RolePermission(actionType=ActionType.DELETE(type=2), resource=Resource(identity=f1, name=file1)), RolePermission(actionType=ActionType.READ(type=0), resource=Resource(identity=f2, name=file2))])})
check_user_access u1 f1 WRITE
user access: true
remove_user_from_role u1 r2
user after removal: User(identity=u1, name=anu, roles={r1=Role(identity=r1, name=admin, rolePermissions=[RolePermission(actionType=ActionType.READ(type=0), resource=Resource(identity=f1, name=file1)), RolePermission(actionType=ActionType.DELETE(type=2), resource=Resource(identity=f1, name=file1)), RolePermission(actionType=ActionType.READ(type=0), resource=Resource(identity=f2, name=file2))])})
check_user_access u1 f1 WRITE
user access: false