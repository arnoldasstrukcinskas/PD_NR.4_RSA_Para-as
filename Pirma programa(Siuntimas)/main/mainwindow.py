from gui.form import FormWidget
from PyQt5.QtCore import Qt
from PyQt5.QtGui import QCloseEvent
from PyQt5.QtWidgets import *

from main.mainwindow_ui import Ui_MainWindow


class MainWindow(Ui_MainWindow, QMainWindow):
    def __init__(self):
        super().__init__()
        self.setupUi(self)
        self.formWidget = FormWidget(self)
        self.verticalLayout_2.addWidget(self.formWidget)

    def closeEvent(self, event: QCloseEvent) -> None:
        self.formWidget.encryptor.close_socket()
        event.accept()
