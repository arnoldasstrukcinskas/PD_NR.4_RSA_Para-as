import json
import socket

from Crypto.Hash import SHA256
from Crypto.PublicKey import RSA
from PyQt5.QtCore import *
from PyQt5.QtWidgets import QMessageBox

IP = "127.0.0.1"
CHANGER_PORT = 1233
RECEIVER_PORT = 1234


class Encryptor(QObject):
    changerPort = CHANGER_PORT
    receiverPort = RECEIVER_PORT

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
        self.sock = None
        self.connected_port: int = None

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

    def connect_to_socket(self, port: int):
        if self.connected_port == port:
            return

        if self.sock is not None:
            self.close_socket()

        try:
            self.sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self.sock.connect((IP, port))
            self.connected_port = port
        except Exception as e:
            self.sock.close()
            print(f"Conenction lost {e}")

    def send_socket_message(self):
        data = {
            "eValue": str(self.eValue),
            "nValue": str(self.nValue),
            "initial_text": self.text,
            "signature": str(self.signature),
        }

        message = json.dumps(data) + "\n"
        try:
            self.sock.sendall(message.encode("utf-8"))
        except Exception as e:
            print(f"Failed to send message: {e}")

    def close_socket(self):
        try:
            if self.sock:
                self.sock.shutdown(socket.SHUT_RDWR)
                self.sock.close()
                self.sock = None
                print("Socket closed")
        except OSError as e:
            print(f"Socket close error: {e}")
