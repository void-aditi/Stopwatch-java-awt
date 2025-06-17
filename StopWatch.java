/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package StopWatch;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aditi Pawar
 */

class Myframes extends Frame implements Runnable
{
    Panel pnlmain, pnlmin, pnlsec, pnlmili;
    Label lblmain, lblmin, lblsec, lblmili;
    Button btnstart, btnstop, btnreset;
    Thread tsec, tmili;
    volatile long min = 0l, sec = 0l, mili = 0l;
    public volatile boolean flag = false;
    
    Myframes()
    {
        this.setLayout(null);
        this.setSize(800, 600);
        this.setBackground(new Color(30, 158, 152));
        this.setLocationRelativeTo(null);
        this.setTitle("STOP-WATCH");
        
        this.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e) 
            {
                Myframes temp = (Myframes)(e.getSource());
                temp.dispose();
            }
        });
        
        addPanels();
        addLabels();
        addButtons();
        
        this.setVisible(true);
    }
    
    final public void addPanels()
    {
        
        pnlmain = new Panel();
        pnlmain.setLayout(null);
        pnlmain.setBounds(20, 50, 760, 80);
        pnlmain.setBackground(new Color(10, 255, 243));
        this.add(pnlmain);
        
        pnlmin = new Panel();
        pnlmin.setLayout(null);
        pnlmin.setBounds(80, 250, 170, 150);
        pnlmin.setBackground(new Color(10, 255, 243));
        this.add(pnlmin);
        
        pnlsec = new Panel();
        pnlsec.setLayout(null);
        pnlsec.setBounds(313, 250, 170, 150);
        pnlsec.setBackground(new Color(10, 255, 243));
        this.add(pnlsec);
        
        pnlmili = new Panel();
        pnlmili.setLayout(null);
        pnlmili.setBounds(550, 250, 170, 150);
        pnlmili.setBackground(new Color(10, 255, 243));
        this.add(pnlmili);
    }

    final public void addLabels()
    {
        lblmain = new Label("STOPWATCH");
        lblmain.setAlignment(Label.CENTER);
        lblmain.setBounds(120, 8, 500, 70);
        lblmain.setFont(new Font("Tahoma", Font.BOLD, 45));
        lblmain.setForeground(new Color(14, 128, 142));
        pnlmain.add(lblmain);
        
        lblmin = new Label();
        lblmin.setText("00");
        lblmin.setAlignment(Label.CENTER);
        lblmin.setBounds(55, 55, 60, 40);
        lblmin.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblmin.setForeground(new Color(53, 138, 138));
        pnlmin.add(lblmin, BorderLayout.CENTER);
        
        lblsec = new Label();
        lblsec.setText("00");
        lblsec.setAlignment(Label.CENTER);
        lblsec.setBounds(55, 55, 60, 40);
        lblsec.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblsec.setForeground(new Color(53, 138, 138));
        pnlsec.add(lblsec, BorderLayout.CENTER);
        
        lblmili = new Label();
        lblmili.setText("00");
        lblmili.setAlignment(Label.CENTER);
        lblmili.setBounds(55, 55, 60, 40);
        lblmili.setFont(new Font("Tahoma", Font.BOLD, 50));
        lblmili.setForeground(new Color(53, 138, 138));
        pnlmili.add(lblmili, BorderLayout.CENTER);
        
    }

    final public void addButtons()
    {
        MyActionListenerS mal = new MyActionListenerS(this);
        
        btnstart = new Button("START");
        btnstart.setBounds(80, 430, 170, 70);
        btnstart.setBackground(Color.red);
        btnstart.setForeground(Color.YELLOW);
        btnstart.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 20));
        this.add(btnstart);
        
        btnstop = new Button("STOP");
        btnstop.setBounds(313, 430, 170, 70);
        btnstop.setBackground(Color.red);
        btnstop.setForeground(Color.YELLOW);
        btnstop.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 20));
        this.add(btnstop);
        
        btnreset = new Button("RESET");
        btnreset.setBounds(550, 430, 170, 70);
        btnreset.setBackground(Color.red);
        btnreset.setForeground(Color.YELLOW);
        btnreset.setFont(new Font("Tahoma", Font.ROMAN_BASELINE, 20));
        this.add(btnreset);
        
        btnstart.addActionListener(mal);
        btnstop.addActionListener(mal);
        btnreset.addActionListener(mal);
    }
    
    final public void startThreads()
    {
        tsec = new Thread(this, "sec");
        tmili = new Thread(this, "mili");
        tsec.start();
        tmili.start();
    }
    
    public void reset()
    {
        mili = 00l;
        lblmili.setText("00");
        
        sec = 00l;
        lblsec.setText("00");
        
        min = 00l;
        lblmin.setText("00");
    }

    @Override
    public void run() 
    {

        while(flag)
        {
            if(Thread.currentThread().getName().equalsIgnoreCase("mili"));
            {
                try 
                {
                    Thread.sleep(1);
                } 
                catch (InterruptedException ex) 
                {
                    System.out.println("Exception ::" + ex);
                }
                mili++;
                lblmili.setText(String.valueOf(mili));
                if(mili == 100l)
                {
                    mili = 0l;
                }
            }
            if(Thread.currentThread().getName().equalsIgnoreCase("sec"))
            {
                try 
                {
                    Thread.sleep(1000);
                } 
                catch (InterruptedException ex) 
                {
                    System.out.println("Exception :: " + ex);
                }
                sec++;
                lblsec.setText(String.valueOf(sec));
                if(sec == 60l)
                {
                    sec = 0l;
                    min++;
                    lblmin.setText(String.valueOf(min));
                }
            }
        }
    }
    
}

class MyActionListenerS implements ActionListener
{
    Myframes ref;
    
    MyActionListenerS(Myframes frame)
    {
        ref = frame;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) 
    {
     
        Button btn = (Button)(e.getSource());
        if(btn.getActionCommand().equals("START"))
        {
            System.out.println("Started");
            ref.flag = true;
            ref.startThreads();
        }
        if(btn.getActionCommand().equals("STOP"))
        {
            System.out.println("Stopped");
            ref.flag = false;
        }
        if(btn.getActionCommand().equals("RESET"))
        {
            System.out.println("Reseted");
            ref.reset();
        }
    }
    
}

public class StopWatch 
{
    public static void main(String[] args)
    {
        Myframes page = new Myframes();
    }
}
