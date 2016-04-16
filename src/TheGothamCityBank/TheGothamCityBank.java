package TheGothamCityBank;
import java.io.*;
import static java.lang.Integer.parseInt;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.Object;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author yaman
 */
class Fileio {
    Scanner scan1,scan,scan2;
    File f =new File("rate.txt");
    File f1 =new File("dist.txt");
    //File f2 =new File("fare.txt");
    static double rate;
    static int rate1,rate2;
    public double[][] mat=new double[6][6];
    public int[][] mat1=new int[6][6];
    //public int[][] mat2=new int[6][6];
    PrintWriter writer;
    public void openr()
    {
        try
        {
            scan = new Scanner(f);
            
    //        scan2 = new Scanner(f2);
        }
        catch(FileNotFoundException e){
            ;
        }
        try
        {
            scan1 = new Scanner(f1);
            
    //        scan2 = new Scanner(f2);
        }
        catch(FileNotFoundException e){
            ;
            System.out.println("error!!!!!");
        }
    }
    public void openw()
    {
        try
        {
            writer = new PrintWriter(f, "UTF-8");
        }
       
         catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Fileio.class.getName()).log(Level.SEVERE, null, ex);
         } catch (FileNotFoundException ex) {
            Logger.getLogger(Fileio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void read()
    {
        this.openr();
        int i=0,j=0;
        do{
            rate=scan.nextDouble();
            mat[i/6][j%6]=rate;
            i++;j++;
        }while(scan.hasNextDouble());
        i=0;j=0;
        do{
            rate1=scan1.nextInt();
            mat1[i/6][j%6]=rate1;
            System.out.print(rate1+" ");
            i++;j++;
        }while(scan1.hasNextInt());
        i=0;j=0;
      /*  do{
            rate2=scan2.nextInt();
            mat2[i/6][j%6]=rate2;
            i++;j++;
        }while(scan2.hasNextInt());
        */
        scan.close();
        scan1.close();
       // scan2.close();
    }
    
    public void upd()
    {
         String r;
     //   double[][] mat=new double[6][6];
    
        try {
            URL url = new URL("http://download.finance.yahoo.com/d/quotes.csv?s=USDGBP=X,USDINR=X,USDAUD=X,USDEUR=X,USDJPY=X,GBPINR=X,GBPAUD=X,GBPEUR=X,GBPJPY=X,INRAUD=X,INREUR=X,INRJPY=X,AUDEUR=X,AUDJPY=X,EURJPY=X&f=sl1d1t1ban&e=.csv");
            HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
            Scanner s = new Scanner(httpConn.getInputStream());
            int i=0,j=1;
            do
            {
                if(j==6){mat[i+1][i+1]=1.0;j=i+2;i++;}
                r=s.next();
                int ctr=11;
                Double mon=0.0;
                while(r.charAt(ctr)!=',')
                {
                    ctr++;
                }
                mon = Double.parseDouble(r.substring(11, ctr));
                mat[i][j]=mon;
                //System.out.println(mon+" "); 
                mat[j][i]=1.0/mon;
                //System.out.println(i + ","+j + "-"+mat[i][j]+" ");
                
                j++;
                //System.out.print(mon+" ");
            }while(s.hasNext());
            mat[0][0]=mat[5][5]=1.0;
           this.openw();
            for(i=0;i<6;++i)
            {
                for(j=0;j<6;++j)
                    writer.println(mat[i][j]+"");
                //writer.println("\n");
            }
            writer.close();
//*/
   // read from your scanner
}
catch(IOException ex) {
   // there was some connection problem, or the file did not exist on the server,
   // or your URL was not in the right format.
   // think about what to do now, and put it here.
  // Scanner scan;
    //File f =new File("rate.txt");
    
            //ex.printStackTrace(); // for now, simply output it.
}
    }
}
class account
{
    public String name,dob,no,typ,last_log,trans1,trans2,trans3,pass;
    public int bal;

    account(Scanner scan) {
        this.name = new String(scan.nextLine());
        //System.out.println(this.name);
         this.dob= new String(scan.nextLine());
         //System.out.println(this.dob);
         this.no  = new String(scan.nextLine());
        // System.out.println(this.no);  
         this.typ= new String(scan.nextLine());
        // System.out.println(this.typ); 
         this.last_log = new String(scan.nextLine());
        // System.out.println(this.last_log);    
         this.pass= new String(scan.nextLine());
          // System.out.println(this.pass);
         this.bal= new Integer(scan.nextLine());
        // System.out.println(this.bal);     
         this.trans1 = new String(scan.nextLine());
        // System.out.println(this.trans1);       
         this.trans2= new String(scan.nextLine());
        // System.out.println(this.trans2);       
         this.trans3= new String(scan.nextLine());
        // System.out.println(this.trans3);
    }

    public void swap(String n)
    {
        trans3=new String(trans2);
        trans2=new String(trans1);
        trans1=new String(n);
    }
    public void init(String D,int w,int a,int n)
    {
        if(n==1)
        {
            trans1=new String(D+" "+w+" "+a+"");
            return;
        }
        if(n==2)
        {
            trans2=new String(D+" "+w+" "+a+"");
            return;
        }
        if(n==3)
        {
            trans3=new String(D+" "+w+" "+a+"");
            return;
        }
    }
}
class trans
{
    File f;
    Scanner scan;
    public account[] acc=new account[10];
    int flag;
    
    
    PrintWriter writer;

    trans() {
        this.f = new File("acc.txt");
        try {
            this.scan = new Scanner(f);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(trans.class.getName()).log(Level.SEVERE, null, ex);
        }
        int i=0;
        for(i=0;i<10;i++)
        this.acc[i]=new account(scan);
    }
    public void openw()
    {
        try
        {
           this.writer = new PrintWriter(f, "UTF-8");
        }
       
         catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Fileio.class.getName()).log(Level.SEVERE, null, ex);
         } catch (FileNotFoundException ex) {
            Logger.getLogger(Fileio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public int read(String no,String pass)
    {
        flag=-1;
        int i=new Integer(0),j=new Integer(0);
        for(i=0;i<10;i++)
        {
         
            //acc[i].name=scan.next();
            /*System.out.println(scan.next());
            acc[i].dob=scan.next();
            acc[i].no=scan.next();
            acc[i].typ=scan.next();
            acc[i].last_log=scan.next();
            acc[i].pass=scan.next();
            acc[i].bal=scan.nextInt();
            acc[i].trans1=scan.next();
            acc[i].trans2=scan.next();
            acc[i].trans3=scan.next();
            */
            if(flag==-1 && no.equals(acc[i].no))
            {
                if(pass.equals(acc[i].pass))
                {
                    flag=i;
                }
            }
        }
        scan.close();
        return flag;
    }
    
    public void upd()
    {
        int i;
        this.openw();
        for(i=0;i<10;++i)
         {
            writer.println(acc[i].name);
            writer.println(acc[i].dob);
            writer.println(acc[i].no);
            writer.println(acc[i].typ);
            writer.println(acc[i].last_log);
            writer.println(acc[i].pass);
            writer.println(acc[i].bal+"");
            writer.println(acc[i].trans1);
            writer.println(acc[i].trans2);
            writer.println(acc[i].trans3);
        }
            writer.close();
    }
}
public class TheGothamCityBank extends javax.swing.JFrame {

    /**
     * Creates new form AirTrafficController
     */
    private Fileio obj=new Fileio();
    public int getcode(String comp)
    {
        if(comp.equals("US Dollar (USD)"))
            return 0;
        if(comp.equals(" British Pound  (GBP)"))
            return 1;
        if(comp.equals(" Indian Ruppee  (INR)"))
            return 2;
        if(comp.equals(" Aus. Dollar (AUD)"))
            return 3;
        if(comp.equals(" Euro  (EUR)"))
            return 4;
        if(comp.equals(" Yen  (JPY)"))
            return 5;
        return 0;
    }
    public TheGothamCityBank() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jTextField5 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextFieldno = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jPasswordFieldpass = new javax.swing.JPasswordField();
        jTextField10 = new javax.swing.JTextField();
        jTextField11 = new javax.swing.JTextField();
        jTextFieldname = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextFielddob = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextFieldno1 = new javax.swing.JTextField();
        jTextFieldtyp = new javax.swing.JTextField();
        jTextFieldlast_log = new javax.swing.JTextField();
        jTextFieldbal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButtondep = new javax.swing.JButton();
        jButtonwith = new javax.swing.JButton();
        jTextFielddep = new javax.swing.JTextField();
        jTextFieldwith = new javax.swing.JTextField();
        jButtonres = new javax.swing.JButton();
        jButtonsubmit = new javax.swing.JButton();
        jTextField18 = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jTextField4 = new javax.swing.JTextField();
        jButtonExchange = new javax.swing.JButton();
        jTextFieldprint = new javax.swing.JTextField();
        jTextFieldfrom = new javax.swing.JTextField();
        jTextFieldto = new javax.swing.JTextField();
        jButtonUpdate = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(738, 590));

        jTabbedPane1.setToolTipText("The Gotham City Bank");
        jTabbedPane1.setName("The Gotham City Bank"); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(750, 561));

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(1, 1, 1));
        jTextField5.setFont(new java.awt.Font("Dialog", 1, 28)); // NOI18N
        jTextField5.setForeground(new java.awt.Color(251, 251, 251));
        jTextField5.setText("            The Bank Of Gotham City");
        jTextField5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField5ActionPerformed(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TheGothamCityBank/index.png"))); // NOI18N

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField6.setForeground(new java.awt.Color(1, 1, 1));
        jTextField6.setText("Account Details");

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jTextField7.setText("  Login");

        jTextFieldno.setText("00000000000");

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField9.setText("  Account No.:");

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField8.setText("Password:");

        jPasswordFieldpass.setText("jPasswordField1");

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField10.setText("Login Pending!");

        jTextField11.setEditable(false);
        jTextField11.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField11.setText("Name:");

        jTextFieldname.setText("Anon");

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField12.setText("DOB:");

        jTextFielddob.setText("1/1/2016");

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField13.setText("Account No.:");
        jTextField13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField13ActionPerformed(evt);
            }
        });

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField14.setText("Balance:");

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField15.setText("Last Transactions:");

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField16.setText("Type:");

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField17.setText(" Last Login:");

        jTextFieldno1.setText("00000000000");

        jTextFieldtyp.setText("Gold");
        jTextFieldtyp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtypActionPerformed(evt);
            }
        });

        jTextFieldlast_log.setText("1/1/2016");

        jTextFieldbal.setText("0000000");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButtondep.setText("Deposit");
        jButtondep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtondepActionPerformed(evt);
            }
        });

        jButtonwith.setText("Withdraw");
        jButtonwith.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonwithActionPerformed(evt);
            }
        });

        jTextFielddep.setText("0000");

        jTextFieldwith.setText("0000");

        jButtonres.setText("Reset");
        jButtonres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonresActionPerformed(evt);
            }
        });

        jButtonsubmit.setText("Login");
        jButtonsubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonsubmitActionPerformed(evt);
            }
        });

        jTextField18.setEditable(false);
        jTextField18.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        jTextField18.setText("Welcome to Gotham City Bank!!");

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TheGothamCityBank/anon.jpg"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField11)
                                    .addComponent(jTextField13)
                                    .addComponent(jTextField14)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jTextFieldno, javax.swing.GroupLayout.DEFAULT_SIZE, 102, Short.MAX_VALUE)
                                    .addComponent(jTextFieldname)
                                    .addComponent(jTextFieldno1)
                                    .addComponent(jTextFieldbal))
                                .addGap(71, 71, 71)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jTextField8)
                                        .addComponent(jTextField12)
                                        .addComponent(jTextField16))
                                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jPasswordFieldpass)
                                    .addComponent(jTextFielddob)
                                    .addComponent(jTextFieldlast_log)
                                    .addComponent(jTextFieldtyp)))))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(347, 347, 347)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jButtonsubmit)
                        .addGap(136, 136, 136)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonres, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(88, 88, 88)))
                .addGap(44, 44, 44))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonwith, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtondep, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldwith, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                    .addComponent(jTextFielddep))
                .addGap(121, 121, 121))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jTextField15)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(297, 297, 297))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 486, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 87, Short.MAX_VALUE)
                    .addComponent(jTextField5))
                .addGap(4, 4, 4)
                .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPasswordFieldpass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonres)
                    .addComponent(jButtonsubmit))
                .addGap(18, 18, 18)
                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFielddob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldno1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldtyp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldlast_log, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldbal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtondep)
                            .addComponent(jTextFielddep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButtonwith)
                            .addComponent(jTextFieldwith, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Account Details", jPanel5);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TheGothamCityBank/index.png"))); // NOI18N

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(1, 1, 1));
        jTextField1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jTextField1.setForeground(new java.awt.Color(251, 251, 251));
        jTextField1.setText("              The Tour Guides Of Gotham City");
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField3.setText("Currency I have:");

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField2.setText("Currency I Want:");
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "US Dollar (USD)", "British Pound  (GBP)", "Indian Ruppee  (INR)", "Aus. Dollar (AUD)", "Euro  (EUR)", "Yen  (JPY)" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "US Dollar (USD)", "British Pound  (GBP)", "Indian Ruppee  (INR)", "Aus. Dollar (AUD)", "Euro  (EUR)", "Yen  (JPY)" }));
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jTextField4.setText("Exchange Rate");

        jButtonExchange.setText("Exchange !!");
        jButtonExchange.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExchangeActionPerformed(evt);
            }
        });

        jTextFieldprint.setText("0.0");
        jTextFieldprint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldprintActionPerformed(evt);
            }
        });

        jTextFieldfrom.setText("Amount");

        jTextFieldto.setText("Amount");
        jTextFieldto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldtoActionPerformed(evt);
            }
        });

        jButtonUpdate.setText("Update thy values!");
        jButtonUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonUpdateActionPerformed(evt);
            }
        });

        jButtonReset.setText("Reset");
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TheGothamCityBank/0.png"))); // NOI18N

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TheGothamCityBank/0.png"))); // NOI18N

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TheGothamCityBank/arrow.jpeg"))); // NOI18N

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/TheGothamCityBank/00.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextFieldfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonReset)
                                .addGap(101, 101, 101))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(76, 76, 76)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jButtonExchange, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(104, 104, 104)
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(30, 30, 30)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jTextFieldprint, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jTextFieldto, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1)
                        .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 597, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField1)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(3, 3, 3)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButtonReset)
                        .addGap(18, 18, 18)
                        .addComponent(jButtonExchange))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldfrom, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldto, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldprint, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(101, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Currency Converter", jPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 590, Short.MAX_VALUE)
        );

        jTabbedPane1.getAccessibleContext().setAccessibleName("BookyourFlight");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jTextFieldprintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldprintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldprintActionPerformed

    private void jTextField5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField5ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField5ActionPerformed

    private void jButtonUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonUpdateActionPerformed
        obj.upd();
        jTextFieldto.setText("Updated");
        jTextFieldfrom.setText("Updated");
        jTextFieldprint.setText("New Values!!");
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonUpdateActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        // TODO add your handling code here:
        jTextFieldto.setText("");
        jTextFieldprint.setText("");
        jTextFieldfrom.setText("");
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        File f=new File("rate.txt");
        Scanner scan;
        try {
            scan = new Scanner(f);
            int i=0,j=0;
        for(i=0;i<6;i++)
        {
            for(j=0;j<6;j++)
            {
                obj.mat[i][j]= scan.nextDouble();
            }
        }
         scan.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheGothamCityBank.class.getName()).log(Level.SEVERE, null, ex);
            
        }
       Scanner scan1;
        try {
            scan1 = new Scanner(new File("dist.txt"));
            int i=0,j=0;
        for(i=0;i<6;i++)
        {
            for(j=0;j<6;j++)
            {
                obj.mat1[i][j]= scan1.nextInt();
            }
        }
         scan1.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TheGothamCityBank.class.getName()).log(Level.SEVERE, null, ex);
            
        }
// obj.upd();
    }//GEN-LAST:event_jButtonResetActionPerformed

    private void jTextField13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField13ActionPerformed

    private void jTextFieldtypActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtypActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtypActionPerformed

    private void jButtonsubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonsubmitActionPerformed
        trans obj=new trans();
        int i;
        i=obj.read(jTextFieldno.getText(), new String(jPasswordFieldpass.getPassword()));
        if(i==-1)
        {
            jTextField10.setText("Try Again!");
        }
        else
        {
            jTextField10.setText("Login Successful!");
        jTextFieldno.setText(obj.acc[i].no);
        jPasswordFieldpass.setText(obj.acc[i].pass);
        jTextFieldname.setText(obj.acc[i].name);
        jTextFieldno1.setText(obj.acc[i].no);
        jTextFieldbal.setText(obj.acc[i].bal+"");
        jTextFielddob.setText(obj.acc[i].dob);
        jTextFieldtyp.setText(obj.acc[i].typ);
        jTextFielddep.setText("00000");
        jTextFieldwith.setText("00000");
        jTextArea1.setText(obj.acc[i].trans1 +"\n"+ obj.acc[i].trans2+"\n"+obj.acc[i].trans3);
        jTextFieldlast_log.setText(obj.acc[i].last_log);
        DateFormat form= new SimpleDateFormat("dd/MM/yyyy");
        
        Date date=new Date();
        System.out.print(date);
        obj.acc[i].last_log=form.format(date);
        ImageIcon img= new ImageIcon("./src/TheGothamCityBank/"+obj.acc[i].name+".jpg");
        jLabel6.setIcon(img);
        obj.upd();
        //obj.acc[i].last_log="2/2/2016";
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonsubmitActionPerformed

    private void jButtonresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonresActionPerformed
        jTextField10.setText("Login Pending!");
        jTextFieldno.setText("00000000000");
        jPasswordFieldpass.setText("**********");
        jTextFieldname.setText("Anon");
        jTextFieldno1.setText("00000000000");
        jTextFieldbal.setText("0000000");
        jTextFielddob.setText("1/1/2016");
        jTextFieldtyp.setText("Gold");
        jTextFielddep.setText("00000");
        jTextFieldwith.setText("00000");
        jTextArea1.setText("");
        jTextFieldlast_log.setText("1/1/2016");
        jTextField18.setText("Welcome to Gotham City Bank");
        ImageIcon img= new ImageIcon("./src/TheGothamCityBank/"+"anon.jpg");
        jLabel6.setIcon(img);
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonresActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jButtondepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtondepActionPerformed
        // TODO add your handling code here:
        trans obj=new trans();
        int i;
        i=obj.read(jTextFieldno.getText(), new String(jPasswordFieldpass.getPassword()));
        if(i==-1)
        {
            jTextField10.setText("Login First!");
            jTextField18.setText("Login First!");
        }
        else
        {
        String depo=jTextFielddep.getText();
        obj.acc[i].bal+=parseInt(depo);
        
        DateFormat form= new SimpleDateFormat("dd/MM/yyyy");
        Date date=new Date();
        obj.acc[i].swap(form.format(date)+" +"+depo+" "+obj.acc[i].bal+"");
        jTextFieldbal.setText(obj.acc[i].bal+"");
        obj.upd();
        jTextField18.setText("Transaction Successful!!");
        }
    }//GEN-LAST:event_jButtondepActionPerformed
    
    private void jButtonwithActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonwithActionPerformed
        // TODO add your handling code here:
        trans obj=new trans();
        int i;
        i=obj.read(jTextFieldno.getText(), new String(jPasswordFieldpass.getPassword()));
        if(i==-1)
        {
            jTextField10.setText("Login First!");
            jTextField18.setText("Login First!");
        }
        else
        {
        String depo=jTextFieldwith.getText();
        if(obj.acc[i].bal>parseInt(depo))
        {
        obj.acc[i].bal-=parseInt(depo);
        
        DateFormat form= new SimpleDateFormat("dd/MM/yyyy");
        Date date=new Date();
        obj.acc[i].swap(form.format(date)+" -"+depo+" "+obj.acc[i].bal+"");
        jTextFieldbal.setText(obj.acc[i].bal+"");
        obj.upd();
        jTextField18.setText("Transaction Successful!!");
        }
        else
        {
         jTextField18.setText("Sorry! Insufficient Balance!!");   
        }
        }   
    }//GEN-LAST:event_jButtonwithActionPerformed

    private void jTextFieldtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldtoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldtoActionPerformed

    private void jButtonExchangeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExchangeActionPerformed
        int ch1=jComboBox1.getSelectedIndex(),ch2=jComboBox2.getSelectedIndex();
        double from= Double.parseDouble(jTextFieldfrom.getText());
        jTextFieldto.setText(from * obj.mat[ch1][ch2]+"");
        jTextFieldprint.setText(obj.mat[ch1][ch2]+"");
        ImageIcon img= new ImageIcon("./src/TheGothamCityBank/"+ch1+".png");
        jLabel1.setIcon(img);
        jLabel4.setIcon(new ImageIcon("./src/TheGothamCityBank/"+ch2+".png"));
        jLabel7.setIcon(new ImageIcon("./src/TheGothamCityBank/"+ch2+""+ch2+".png"));
        
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonExchangeActionPerformed
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TheGothamCityBank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TheGothamCityBank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TheGothamCityBank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TheGothamCityBank.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new TheGothamCityBank().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonExchange;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonUpdate;
    private javax.swing.JButton jButtondep;
    private javax.swing.JButton jButtonres;
    private javax.swing.JButton jButtonsubmit;
    private javax.swing.JButton jButtonwith;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPasswordField jPasswordFieldpass;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JTextField jTextFieldbal;
    private javax.swing.JTextField jTextFielddep;
    private javax.swing.JTextField jTextFielddob;
    private javax.swing.JTextField jTextFieldfrom;
    private javax.swing.JTextField jTextFieldlast_log;
    private javax.swing.JTextField jTextFieldname;
    private javax.swing.JTextField jTextFieldno;
    private javax.swing.JTextField jTextFieldno1;
    private javax.swing.JTextField jTextFieldprint;
    private javax.swing.JTextField jTextFieldto;
    private javax.swing.JTextField jTextFieldtyp;
    private javax.swing.JTextField jTextFieldwith;
    // End of variables declaration//GEN-END:variables
}
