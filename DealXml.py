from xml.etree import ElementTree as et

class DealXML():
    def loadXMLFile(self):
        return et.parse(self.loadXMLFile())
        
    def readXML(self):
        root = self.loadXMLFile()
        
        print("read")
    
    def addTab(self):
        root = self.loadXMLFile()
        print("add tab")
    
    def addSoft(self):
        root = self.loadXMLFile()
        print("add soft")
    
    def editTab(self):
        root = self.loadXMLFile()
        print("edit tab")
    
    def editSoft(self):
        root = self.loadXMLFile()
        print("edit soft")

a=DealXML()
a.loadXMLFile()
