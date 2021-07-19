# BusinessExercise
My first Spring Boot project made to complete an exercise. Tha project it's a REST Api to manage employees in a business, these employees have assigned roles and work in different departments. We can apply any of the CRUD operations to the three entities (employee, role and department), as well as do some other operations like filter employees by some fields. Some of the operations are restricted by role. Only administrators can create, update or delete any entity, everyone else can access the data but can't modify it. 

For authentication, it uses JWT tokens that we produce when the user logs in, and we check when a new request is called. The JWT has an expiration time of two hours.
