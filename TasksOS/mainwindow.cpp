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
   // textEdit=new QTextEdit();

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
        QStringList list=rowString.split(' ');
        QStringListIterator collumnIterator(list);
        for(int j=0;j<collumns;j++){
            QTableWidgetItem* item=new QTableWidgetItem(collumnIterator.next());
            table->setItem(i,j,item);
        }
    }
    return table;
}
void MainWindow::showOut(){
    //this->textEdit->append(process->readAllStandardOutput());

    this->processTable=makeProcessTable(QString(process->readAllStandardOutput()).split('\n'),QStringList{"uname", "pid", "%mem", "pri", "lwp", "nlwp", "cmd"});

    //this->processTable=MainWindow::makeProcessTable(QStringList{"first second","second first"},QStringList{"name","demo"});

//    this->vLayout->addWidget(processTable);
//    this->widget->setLayout(vLayout);
//    this->widget->show();

    this->setCentralWidget(processTable);


}

QProcess *MainWindow::getProcess() const
{
    return process;
}

void MainWindow::setProcess(QProcess *value)
{
    process = value;
}

