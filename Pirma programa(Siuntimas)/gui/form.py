import subprocess
import time
from pathlib import Path
from typing import TYPE_CHECKING

from logic.encryptor import Encryptor
from PyQt5.QtWidgets import *

from gui.form_ui import Ui_Form

if TYPE_CHECKING:
    from main.mainwindow import MainWindow


class FormWidget(Ui_Form, QWidget):

    def __init__(self, parent: "MainWindow"):
        self.receiverLaunched: bool = False
        super().__init__(parent=parent)
        self.setMinimumSize(400, 400)
        self.setupUi(self)
        self.launch_receiver
        self.encryptor = Encryptor()
        self.sendOnePushButton.clicked.connect(self.send_mesage)
        self.receiverLaunchButton.clicked.connect(self.launch_receiver)
        self.changerLaunchButton.clicked.connect(self.launch_changer)

    def send_mesage(self):
        self.encryptor.text = self.inputTextEdit.toPlainText()
        self.encryptor.rsa_encryption()
        self.update_ui()
        self.encryptor.send_socket_message()

    def update_ui(self):
        self.pValueLineEdit.setText(str(self.encryptor.pValue))
        self.qValueLineEdit.setText(str(self.encryptor.qValue))
        self.globalKeyLineEdit.setText(
            f"Key(pub) = (n, e) = ({self.encryptor.nValue}, {self.encryptor.eValue})"
        )
        self.privateKeyLineEdit.setText(f"Key(priv) = d = ({self.encryptor.dValue})")
        self.nValueTextEdit.setText(str(self.encryptor.nValue))
        self.fiValueTextEdit.setText(str(self.encryptor.fiValue))
        self.signatureTextEdit.setPlainText(str(self.encryptor.signature))

    def launch_receiver(self):
        # For spring management(powershell):
        # netstat -ano | findstr :8082
        # taskkill /PID 30036 /F

        current_dir = Path(__file__).resolve().parent
        app_dir = current_dir.parent.parent / "Trecia_programa_tikrinimas"
        print(f"path: {app_dir}")
        self.receiverProcess = subprocess.Popen(
            ["mvnw.cmd", "spring-boot:run"],
            cwd=app_dir,
            shell=True,
            creationflags=subprocess.CREATE_NEW_PROCESS_GROUP,
        )

        time.sleep(5)
        self.encryptor = Encryptor()
        print("Programą paleidau ir prisijungiau!")

    def launch_changer(self):
        print("aaa")
