from xml.etree import ElementTree as et
from SoftVo import Soft

class DealXML():
    def loadXMLFile(self):
        return et.parse('conf/list.xml').getroot()
        
    def readXML(self):
        root = self.loadXMLFile()
        softs = root.find("tab").findall("soft")
        softList = []
        for soft in softs:
            vo = Soft()
            vo.name = soft.get("name")
            vo.foldername = soft.get("foldername")
            vo.filename = soft.get("filename")
            vo.row = soft.get("row")
            vo.column = soft.get("column")
            vo.cmd = soft.get("cmd")
            paramList=soft.getchildren()
            for param in paramList:
                vo.param.append(param.text)
            softList.append(vo)
        return softList
    
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
    
    def delTab(self):
        root = self.loadXMLFile()
        print("del tab")
    
    def delSoft(self):
        root = self.loadXMLFile()
        print("del soft")

a=DealXML()
a.loadXMLFile()
print(a.readXML())
