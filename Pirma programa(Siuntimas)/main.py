import sys

from main.mainwindow import MainWindow
from PyQt5.QtWidgets import QApplication

if __name__ == "__main__":
    app = QApplication(sys.argv)

    screen = MainWindow()
    screen.resize(500, 700)
    screen.show()

    sys.exit(app.exec_())
