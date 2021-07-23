#!groovy

def call(String name = "Task") {
    script {
        sh """
            echo Proceeding task ${name}
        """
    }
}
