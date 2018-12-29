/* Jenkins Job DSL Demo Script */
 
def interfaces = ['foo-interfaceA', 'foo-interfaceB', 'foo-interfaceC', 'foo-interfaceD', 'foo-interfaceE', 'foo-interfaceF']
def release = 'R1812' 

interfaces.each{
  def interface = $it; 
  def interfaceName = "$release-$interface"; 
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

