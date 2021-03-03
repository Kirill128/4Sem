#include "mainwindow.h"
#include "ui_mainwindow.h"

MainWindow::MainWindow(QWidget *parent)
    : QMainWindow(parent)
    , ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    process=new QProcess(this);
    processTable=new QTableWidget(0,0,this);


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

void removeSuperflous(QStringList *source,int maxLength){
    while(source->count()>maxLength){
        source->removeLast();
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
        removeSuperflous(&resultRows->last(),7);
    }
    return resultRows;
}

void processThread(QList<QStringList> *source){
    QMutableListIterator<QStringList> rowIterator(*source);

    QStringList *firstUniqueThreadRow= &rowIterator.next();//1-st item
    QStringList *nextRow;

    QString res=firstUniqueThreadRow->at(4)+" "+firstUniqueThreadRow->at(3)+"\n";
    while(rowIterator.hasNext()){
        nextRow=&rowIterator.next();
        if(!QString::compare(firstUniqueThreadRow->at(1),nextRow->at(1))){
            res+=nextRow->at(4)+" "+nextRow->at(3)+"\n";//light wage process id + priority
            rowIterator.remove();
        }else{
            firstUniqueThreadRow->append(res);
            firstUniqueThreadRow=nextRow;
            res=firstUniqueThreadRow->at(4)+" "+firstUniqueThreadRow->at(3)+"\n";
         }
    }

}
void MainWindow::makeProcessTable(QList<QStringList> source,QStringList collumnNames,QTableWidget* table){

        const int rows=source.count()+table->rowCount();
        const int collumns=collumnNames.count();
        int table_row=table->rowCount();
        if(table->columnCount()!=collumns){
            table->setColumnCount(collumns);
            table->setHorizontalHeaderLabels(collumnNames);
            table->setColumnWidth(6,200);
            table->setColumnWidth(7,200);

        }
        table->setRowCount(rows);

        QListIterator<QStringList> rowIterator(source);

        for(int i=table_row;i<rows;i++){
            QStringList row=rowIterator.next();
            QStringListIterator collumnIterator(row);

                for(int j=0;j<row.count();j++){
                    QTableWidgetItem* item=new QTableWidgetItem(collumnIterator.next());
                    table->setItem(i,j,item);

                }
                table->setRowHeight(i,100);
         }


}
void MainWindow::showOut(){

        QStringList rows=QString(process->readAllStandardOutput()).split('\n');
        rows.removeFirst();
        rows.removeLast();
        QList<QStringList>* res(parse(rows));
        processThread(res);
//        qDebug()<<*res;
        makeProcessTable(*res,QStringList{"uname", "process id", "%mem", "priority", "main thread id", "count threads", "cmd","threat id and priority"},this->processTable);
        this->setCentralWidget(this->processTable);
        delete res;
}

QProcess *MainWindow::getProcess() const
{
    return process;
}

void MainWindow::setProcess(QProcess *value)
{
    process = value;
}

