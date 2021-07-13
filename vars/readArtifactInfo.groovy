def call(def result) {
    read_pom_file(result)
}


def read_pom_file(def buildStatus) {
    def pom = readMavenPom file: 'pom.xml'
    ARTIFACT_VERSION = pom.version
    ARTIFACT_PKG_NAME = pom.packaging
    echo "LOG->INFO : ARTIFACT_VERSION is ${ARTIFACT_VERSION}"
    echo "LOG->INFO : ARTIFACT_PKG_NAME is ${ARTIFACT_PKG_NAME}"
}
