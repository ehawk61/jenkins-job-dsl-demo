/* Jenkins Job DSL Demo Script Revisited*/
 
def interfaces = ['foo-interfaceA', 'foo-interfaceB', 'foo-interfaceC', 'foo-interfaceD', 'foo-interfaceE', 'foo-interfaceF']

def release = "$RELEASE" 

interfaces.each{
  def interfaceName = "$release-$it"; 
  freeStyleJob(interfaceName){
    jdk('1.7')
    scm{
      svn("svn://svn.mydomain.com/project1/$release/" + interfaceName.replaceAll("$release-",""))
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

