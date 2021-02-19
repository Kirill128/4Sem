#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    process=new QProcess(this);


    connect(process,SIGNAL(readyReadStandardOutput()),this,SLOT(showOut()));
    process->start("ps",QStringList{"-e","--format","uname pid %mem pri lwp nlwp cmd"});
}

MainWindow::~MainWindow()
{
    for(int i=0;i<processTable->rowCount();i++){
        for(int j=0;j<processTable->columnCount();j++)
        processTable->item(i,j);
    }
    delete this->processTable;
    delete ui;
}
QTableWidget* MainWindow::makeProcessTable(QStringList source,QStringList collumnNames){
    int rows=source.count();
    int collumns=collumnNames.count();
    QTableWidget* table=new QTableWidget(rows,collumns);

    QStringListIterator rowIterator(source);
    QString rowString;

    table->setHorizontalHeaderLabels(collumnNames);
    for(int i=0;i<rows;i++){
        rowString=rowIterator.next();
        QStringList rowList=rowString.split(' ',Qt::SkipEmptyParts);
        while(rowList.count()>collumns){
            rowList.removeLast();
        }
        QStringListIterator collumnIterator(rowList);
        for(int j=0;j<collumns;j++){
            QTableWidgetItem* item=new QTableWidgetItem(collumnIterator.next());
            table->setItem(i,j,item);
        }
    }
    return table;
}
void MainWindow::showOut(){
    //this->textEdit->append(process->readAllStandardOutput());

//    this->processTable=makeProcessTable(QString(process->readAllStandardOutput()).split('\n'),QStringList{"uname", "pid", "%mem", "pri", "lwp", "nlwp", "cmd"});

    //this->processTable=MainWindow::makeProcessTable(QStringList{"first second","second first"},QStringList{"name","demo"});
    QStringList rows=QString(process->readAllStandardOutput()).split('\n');
    this->processTable=makeProcessTable(rows,QStringList{"uname", "pid", "%mem", "pri", "lwp", "nlwp", "cmd"});
    this->setCentralWidget(this->processTable);

}

QProcess *MainWindow::getProcess() const
{
    return process;
}

void MainWindow::setProcess(QProcess *value)
{
    process = value;
}

