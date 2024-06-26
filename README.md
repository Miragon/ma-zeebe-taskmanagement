# Task-Management in einer Microservice Umgebung

# Setup

1. Build the project
    ```shell
    mvn clean package
    ```

2. Start the stack (this will may take some seconds)
    ```shell
    cd stack
    docker-compose up
    ```
   > :info: You can use the `run scripts` if you are using `Intellij`.

3. Deploy the process
    ```shell
    # Order Process
    zbctl --insecure deploy <PATH_TO_PROJECT>/processes/order/src/main/resources/bpmn/order.bpmn
    ```

4. Deploy forms
   > :info: Use the `Postman-Collection` located in `apps/tasklist/resources` to deploy the forms.

5. Open the [Tasklist](http://localhost:8081)

![overview](images/overview.png)

