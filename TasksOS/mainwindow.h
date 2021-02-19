#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QProcess>
#include <QTableWidget>
#include <QTextEdit>
#include <QVBoxLayout>

QT_BEGIN_NAMESPACE
namespace Ui { class MainWindow; }
QT_END_NAMESPACE

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    MainWindow(QWidget *parent = nullptr);
    ~MainWindow();
    QProcess *getProcess() const;
    void setProcess(QProcess *value);
    QTableWidget* makeProcessTable(QStringList source,QStringList collumnNames);
public slots:
    void showOut();
private:
    Ui::MainWindow *ui;
    QProcess* process;
    QTableWidget* processTable;
};
#endif // MAINWINDOW_H
