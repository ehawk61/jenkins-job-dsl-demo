/* Jenkins Job DSL Demo Script */
 
def interfaces = ['foo-interfaceA', 'foo-interfaceB', 'foo-interfaceC', 'foo-interfaceD', 'foo-interfaceE', 'foo-interfaceF']
def release = 'R1812' 

interfaces.each{
  def interfaceName = "$release-$it"; 
  freeStyleJob(interfaceName){
    jdk('1.7')
    scm{
      svn("https://svn4.sliksvn.com/jonathanme_testsvnrepo/branches/$release/" + interfaceName.replaceAll("$release-",""))
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

