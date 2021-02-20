#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    process=new QProcess(this);
    processTable=new QTableWidget(0,0,this);

    was=false;

    connect(process,SIGNAL(readyReadStandardOutput()),this,SLOT(showOut()));
    process->start("ps",QStringList{"-eL","--format","uname pid %mem pri lwp nlwp cmd"});
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
void MainWindow::makeProcessTable(QStringList source,QStringList collumnNames,QTableWidget* table){
    const int rows=source.count()+table->rowCount();
    const int collumns=collumnNames.count();
    int table_row=table->rowCount();
    if(table->columnCount()!=collumns){
        table->setColumnCount(collumns);
        table->setHorizontalHeaderLabels(collumnNames);
    }
    table->setRowCount(rows);

    QStringListIterator rowIterator(source);
    QString rowString;

    for(int i=table_row;i<rows;i++){
        rowString=rowIterator.next();
        QStringList rowList=rowString.split(' ',Qt::SkipEmptyParts);
        while(rowList.count()>collumns){
            rowList.removeLast();
        }
        QStringListIterator collumnIterator(rowList);
        for(int j=0;j<rowList.count();j++){
            QTableWidgetItem* item=new QTableWidgetItem(collumnIterator.next());
            table->setItem(i,j,item);
        }
    }
}
QList<QStringList>* parse(QStringList source){
    const int rows=source.count();

    QStringListIterator rowIterator(source);
    QString rowString;

    QList<QStringList> *resultRows=new QList<QStringList>();
    for(int i=0;i<rows;i++){
        rowString=rowIterator.next();
        resultRows->append(rowString.split(' ',Qt::SkipEmptyParts));
    }
    return resultRows;
}

void remakeForTreat(QList<QStringList> *source){
    QListIterator<QStringList> rowIterator(*source);
    rowIterator.next();
    QStringList row;
    bool sameProcess=false;
    while(rowIterator.hasNext()){
        row=rowIterator.next();

    }
}

void MainWindow::showOut(){
    //this->textEdit->append(process->readAllStandardOutput());

//    this->processTable=makeProcessTable(QString(process->readAllStandardOutput()).split('\n'),QStringList{"uname", "pid", "%mem", "pri", "lwp", "nlwp", "cmd"});

    //this->processTable=MainWindow::makeProcessTable(QStringList{"first second","second first"},QStringList{"name","demo"});
    //if(!was){
        QStringList rows=QString(process->readAllStandardOutput()).split('\n');
        rows.removeFirst();
        rows.removeLast();

        makeProcessTable(rows,QStringList{"uname", "process id", "%mem", "priority", "light wage pr", "num lwp", "cmd"},this->processTable);

        this->setCentralWidget(this->processTable);
        was=true;
    //}
}

QProcess *MainWindow::getProcess() const
{
    return process;
}

void MainWindow::setProcess(QProcess *value)
{
    process = value;
}

