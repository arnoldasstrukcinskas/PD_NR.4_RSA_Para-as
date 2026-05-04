from Crypto.Hash import SHA256
from Crypto.PublicKey import RSA
from PyQt5.QtCore import *
from PyQt5.QtWidgets import QMessageBox


class Encryptor(QObject):
    def __init__(self):
        super().__init__()
        self.pValue: int = None
        self.qValue: int = None
        self.text: str = None
        self.globalKey: str = None
        self.privateKey: str = None
        self.nValue: int = None
        self.fiValue: int = None
        self.eValue: int = None
        self.dValue: int = None
        self.signature: int = None
        self.message_hash: int = None

    def rsa_encryption(self) -> None:

        key = RSA.generate(1024)
        self.pValue = key.p
        self.qValue = key.q
        self.nValue = key.n
        self.eValue = key.e
        self.dValue = key.d
        self.fiValue = (self.pValue - 1) * (self.qValue - 1)
        self.globalKey = key.p
        self.get_signature()

    def get_signature(self) -> None:
        text_hash = SHA256.new(self.text.encode())
        self.message_hash = int.from_bytes(text_hash.digest(), "big")

        self.signature = pow(self.message_hash, self.dValue, self.nValue)
