# -*- coding: utf-8 -*-

# Form implementation generated from reading ui file 'E:\project\git\SixTools\ui\SixToolsUi.ui'
#
# Created: Mon Aug 25 22:37:51 2014
#      by: PyQt4 UI code generator 4.11.1
#
# WARNING! All changes made in this file will be lost!

from PyQt4 import QtCore, QtGui

try:
    _fromUtf8 = QtCore.QString.fromUtf8
except AttributeError:
    def _fromUtf8(s):
        return s

try:
    _encoding = QtGui.QApplication.UnicodeUTF8
    def _translate(context, text, disambig):
        return QtGui.QApplication.translate(context, text, disambig, _encoding)
except AttributeError:
    def _translate(context, text, disambig):
        return QtGui.QApplication.translate(context, text, disambig)

class Ui_MainWindow(object):
    def setupUi(self, MainWindow):
        MainWindow.setObjectName(_fromUtf8("MainWindow"))
        MainWindow.resize(600, 400)
        MainWindow.setMinimumSize(QtCore.QSize(600, 400))
        MainWindow.setMaximumSize(QtCore.QSize(600, 400))
        self.centralWidget = QtGui.QWidget(MainWindow)
        self.centralWidget.setObjectName(_fromUtf8("centralWidget"))
        self.tabWidget = QtGui.QTabWidget(self.centralWidget)
        self.tabWidget.setGeometry(QtCore.QRect(0, 0, 601, 361))
        sizePolicy = QtGui.QSizePolicy(QtGui.QSizePolicy.Expanding, QtGui.QSizePolicy.Expanding)
        sizePolicy.setHorizontalStretch(0)
        sizePolicy.setVerticalStretch(0)
        sizePolicy.setHeightForWidth(self.tabWidget.sizePolicy().hasHeightForWidth())
        self.tabWidget.setSizePolicy(sizePolicy)
        self.tabWidget.setObjectName(_fromUtf8("tabWidget"))
        self.tab = QtGui.QWidget()
        self.tab.setObjectName(_fromUtf8("tab"))
        self.tabWidget.addTab(self.tab, _fromUtf8(""))
        self.tab_2 = QtGui.QWidget()
        self.tab_2.setObjectName(_fromUtf8("tab_2"))
        self.tabWidget.addTab(self.tab_2, _fromUtf8(""))
        MainWindow.setCentralWidget(self.centralWidget)
        self.menuBar = QtGui.QMenuBar(MainWindow)
        self.menuBar.setGeometry(QtCore.QRect(0, 0, 600, 23))
        self.menuBar.setObjectName(_fromUtf8("menuBar"))
        self.menu_F = QtGui.QMenu(self.menuBar)
        self.menu_F.setObjectName(_fromUtf8("menu_F"))
        self.menu_E = QtGui.QMenu(self.menuBar)
        self.menu_E.setObjectName(_fromUtf8("menu_E"))
        self.menu_T = QtGui.QMenu(self.menuBar)
        self.menu_T.setObjectName(_fromUtf8("menu_T"))
        self.menu_H = QtGui.QMenu(self.menuBar)
        self.menu_H.setObjectName(_fromUtf8("menu_H"))
        MainWindow.setMenuBar(self.menuBar)
        self.statusBar = QtGui.QStatusBar(MainWindow)
        self.statusBar.setObjectName(_fromUtf8("statusBar"))
        MainWindow.setStatusBar(self.statusBar)
        self.toolBar = QtGui.QToolBar(MainWindow)
        self.toolBar.setObjectName(_fromUtf8("toolBar"))
        MainWindow.addToolBar(QtCore.Qt.TopToolBarArea, self.toolBar)
        self.help_action = QtGui.QAction(MainWindow)
        self.help_action.setObjectName(_fromUtf8("help_action"))
        self.about_action = QtGui.QAction(MainWindow)
        self.about_action.setObjectName(_fromUtf8("about_action"))
        self.add_tab_action = QtGui.QAction(MainWindow)
        self.add_tab_action.setObjectName(_fromUtf8("add_tab_action"))
        self.add_soft_action = QtGui.QAction(MainWindow)
        self.add_soft_action.setObjectName(_fromUtf8("add_soft_action"))
        self.del_tab_action = QtGui.QAction(MainWindow)
        self.del_tab_action.setObjectName(_fromUtf8("del_tab_action"))
        self.del_soft_action = QtGui.QAction(MainWindow)
        self.del_soft_action.setObjectName(_fromUtf8("del_soft_action"))
        self.edit_tab_action = QtGui.QAction(MainWindow)
        self.edit_tab_action.setObjectName(_fromUtf8("edit_tab_action"))
        self.edit_soft_action = QtGui.QAction(MainWindow)
        self.edit_soft_action.setObjectName(_fromUtf8("edit_soft_action"))
        self.menu_F.addAction(self.add_tab_action)
        self.menu_F.addAction(self.add_soft_action)
        self.menu_E.addAction(self.del_tab_action)
        self.menu_E.addAction(self.del_soft_action)
        self.menu_E.addAction(self.edit_tab_action)
        self.menu_E.addAction(self.edit_soft_action)
        self.menu_H.addAction(self.help_action)
        self.menu_H.addAction(self.about_action)
        self.menuBar.addAction(self.menu_F.menuAction())
        self.menuBar.addAction(self.menu_E.menuAction())
        self.menuBar.addAction(self.menu_T.menuAction())
        self.menuBar.addAction(self.menu_H.menuAction())
        self.toolBar.addAction(self.add_tab_action)
        self.toolBar.addAction(self.edit_tab_action)
        self.toolBar.addAction(self.del_tab_action)
        self.toolBar.addSeparator()
        self.toolBar.addAction(self.add_soft_action)
        self.toolBar.addAction(self.edit_soft_action)
        self.toolBar.addAction(self.del_soft_action)
        self.toolBar.addSeparator()
        self.toolBar.addAction(self.help_action)

        self.retranslateUi(MainWindow)
        self.tabWidget.setCurrentIndex(0)
        QtCore.QMetaObject.connectSlotsByName(MainWindow)

    def retranslateUi(self, MainWindow):
        MainWindow.setWindowTitle(_translate("MainWindow", "MainWindow", None))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab), _translate("MainWindow", "Tab 1", None))
        self.tabWidget.setTabText(self.tabWidget.indexOf(self.tab_2), _translate("MainWindow", "Tab 2", None))
        self.menu_F.setTitle(_translate("MainWindow", "文件(&F)", None))
        self.menu_E.setTitle(_translate("MainWindow", "编辑(&E)", None))
        self.menu_T.setTitle(_translate("MainWindow", "工具(&T)", None))
        self.menu_H.setTitle(_translate("MainWindow", "帮助(&H)", None))
        self.toolBar.setWindowTitle(_translate("MainWindow", "toolBar", None))
        self.help_action.setText(_translate("MainWindow", "帮助", None))
        self.about_action.setText(_translate("MainWindow", "关于", None))
        self.add_tab_action.setText(_translate("MainWindow", "添加Tab", None))
        self.add_soft_action.setText(_translate("MainWindow", "添加程序", None))
        self.del_tab_action.setText(_translate("MainWindow", "删除Tab", None))
        self.del_soft_action.setText(_translate("MainWindow", "删除程序", None))
        self.edit_tab_action.setText(_translate("MainWindow", "修改Tab", None))
        self.edit_soft_action.setText(_translate("MainWindow", "修改程序", None))


if __name__ == "__main__":
    import sys
    app = QtGui.QApplication(sys.argv)
    MainWindow = QtGui.QMainWindow()
    ui = Ui_MainWindow()
    ui.setupUi(MainWindow)
    MainWindow.show()
    sys.exit(app.exec_())

