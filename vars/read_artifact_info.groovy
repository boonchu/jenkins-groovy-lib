#!groovy

def call() {
    def pom = readMavenPom file: 'pom.xml'

    // https://maven.apache.org/pom.html
    // https://stackoverflow.com/questions/53541489/updating-environment-global-variable-in-jenkins-pipeline-from-the-stage-level/53541813
    withEnv([
		"ARTIFACT_PKG_VERSION=${pom.version}",
		"ARTIFACT_PKG_NAME=${pom.artifactId}",
        "ARTIFACT_PKG_SUFFIX=${pom.packaging}",
        "ARTIFACT_PKG_GROUP=${pom.groupId}"
	]) {
    	echo "CALL->INFO : ARTIFACT_PKG_VERSION is ${env.ARTIFACT_PKG_VERSION}"
    	echo "CALL->INFO : ARTIFACT_PKG_NAME is ${env.ARTIFACT_PKG_NAME}"
    	echo "CALL->INFO : ARTIFACT_PKG_SUFFIX is ${env.ARTIFACT_PKG_SUFFIX}"
    	echo "CALL->INFO : ARTIFACT_PKG_GROUP is ${env.ARTIFACT_PKG_GROUP}"
	}

    sh "echo ${pom.version} > .git/tagName"
    tagName = readFile('.git/tagName')
    echo "CALL->INFO: value read from file .git/tagName as ${tagName}"
    // set DisplayName
    currentBuild.displayName = tagName
}
