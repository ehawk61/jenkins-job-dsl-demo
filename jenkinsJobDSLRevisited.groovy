/* Jenkins Job DSL Demo Script Revisited*/
 
def interfaces = "$INTERFACES".split(",")

def release = "$RELEASE" 

interfaces.each{
  def interfaceName = "$release-$it"; 
  freeStyleJob(interfaceName){
    jdk('1.8')
    scm{
      svn("https://svn4.sliksvn.com/jonathanme_testsvnrepo/jenkinsJobDSLDemo/branches/$release/" + interfaceName.replaceAll("$release-",""))
    }
    steps{
      maven('clean install')
    }
  }
}
listView("$release-foo-interfaces"){
  description("All jobs for $release-foo-interfaces")
  jobs {
    interfaces.each{ name("$release-$it") }
  }
  columns {
    status()
    weather()
    name()
    lastSuccess()
    lastFailure()
    lastDuration()
    buildButton()
  }
}

