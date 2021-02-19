#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    process=new QProcess(this);
    connect(process,SIGNAL(readyReadStandardOutput()),this,SLOT(showOut()));
    process->start("ps",QStringList{"-eL","--format","uname pid %mem pri lwp nlwp cmd"});
}

MainWindow::~MainWindow()
{
    delete ui;
}
void MainWindow::showOut(){
    ui->textEdit->append(process->readAllStandardOutput());

  //  ui->textEdit->append(process->readAllStandardError());
}

QProcess *MainWindow::getProcess() const
{
    return process;
}

void MainWindow::setProcess(QProcess *value)
{
    process = value;
}

