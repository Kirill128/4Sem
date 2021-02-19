#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMainWindow>
#include <QProcess>

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
public slots:
    void showOut();
private:
    Ui::MainWindow *ui;
    QProcess* process;
};
#endif // MAINWINDOW_H
