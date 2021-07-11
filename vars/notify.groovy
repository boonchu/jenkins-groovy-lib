#!groovy

def call(def result) {
	notify_build(result)
}


def notify_build(def buildStatus) {
    buildStatus = buildStatus ?: 'STARTED'
    env.JOB_DISPLAY_NAME = Jenkins.instance.getJob("${env.JOB_NAME}").displayName
    env.PREVIOUS_BUILD_RESULT = currentBuild.rawBuild.getPreviousBuild()?.getResult().toString()

    def colorMap = [ 'STARTED': '#F0FFFF', 'SUCCESS': '#008B00', 'FAILURE': '#FF0000' ]
    def subject = "Pipeline: ${env.JOB_DISPLAYNAME} - #${env.BUILD_NUMBER} ${buildStatus}"
    def summary = "${subject} (${env.BUILD_URL})"
    def colorName = colorMap[buildStatus]

    if ("${buildStatus}" != "${env.PREVIOUS_BUILD_RESULT}") {
        echo "message: ${summary}, color: ${colorName}"
    }
}
