#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    widget=new QWidget();
    process=new QProcess(this);
    vLayout=new QVBoxLayout(this);
    textEdit=new QTextEdit();

    vLayout->addWidget(textEdit);
    widget->setLayout(vLayout);
    widget->show();

    this->setCentralWidget(widget);
    connect(process,SIGNAL(readyReadStandardOutput()),this,SLOT(showOut()));
    process->start("ps",QStringList{"-e","--format","uname pid %mem pri lwp nlwp cmd"});
}

MainWindow::~MainWindow()
{
    delete ui;
}
QTableWidget* MainWindow::makeProcessTable(QStringList source,QStringList collumnNames){
    int rows=source.count();
    int collumns=collumnNames.count();
    QTableWidget* table=new QTableWidget(rows,collumns);

    table->setHorizontalHeaderLabels(collumnNames);
    for(int i=0;i<rows;i++){
        for(int j=0;j<collumns;j++){

            QTableWidgetItem* item=new QTableWidgetItem();
            table->setItem(i,j,item);
        }
    }


    return table;
}
void MainWindow::showOut(){
    this->textEdit->append(process->readAllStandardOutput());

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

