//导入所需的类
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//定义ATMUI类中的方法
public class ATMUI{
	/**
	*主方法
	*/
	public static void main(String [] args){
		ATMUI ui = new ATMUI();
		//初始化业务对象
		ui.initBO();
		//初始化主窗口
		ui.initFrame();
		//显示登录界面
		ui.showLogin();
	}
	
	/**
	*初始化业务对象
	*
	* 业务对象，每一个用户对应一个新的业务对象并保存该用户数据
	* 因此，启动程序和每次退出都要创建一个新的业务对象给下一个
	* 用户使用
	*/
	public ATMBO bo = null ; //这个是啥？？
	public void initBO(){
		bo = new ATMBO();
	}
	
	/**
	*初始化主窗口
	*/
	//界面宽和高
	public int width = 960;
	public int height = 720;
	
	//界面窗口
	public JFrame jFrame = null;
	//层叠容器
	public JLayeredPane layeredPane = null;
	
	//背景层
	public JPanel backLayer = null;
	//前景层
	public JPanel frontLayer = null;
	//前景层布局器
	public CardLayout cardLayout = null;
	
	public void initFrame(){
		//----初始化窗口与层叠容器----
		//创建窗口对象，窗口标题为“ATM触摸屏系统”
		jFrame = new JFrame("ATM触摸屏系统");
		
		//创建层叠容器对象
		layeredPane = new JLayeredPane();
		//设置层叠容器大小
		layeredPane.setPreferredSize(new Dimension(width,height));
		
		//把层叠容器添加到窗口中
		jFrame.add(layeredPane);
		//设置窗口不能放大缩小
		jFrame.setResizable(false);
		//设置窗口大小适应内容（层叠容器）大小
		jFrame.pack();
		//设置窗口可见（默认不可见）
		jFrame.setVisible(true);
		//设置窗口关闭时，程序关闭
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//----在层叠容器中增加背景层----
		//创建背景层对象
		backLayer = new JPanel();
		//设置背景层布局器FlowLayout的水平间距为0（默认为5）
		((FlowLayout)backLayer.getLayout()).setHgap(0);
		//设置背景层布局器FlowLayout的垂直间距为0（默认为5）
		((FlowLayout)backLayer.getLayout()).setVgap(0);
		
		//设置背景层大小（同窗口）
		backLayer.setSize(width,height);
		//设置背景层位置（相对于窗口左上角）
		backLayer.setLocation(0,0);
		
		//创建背景图对象
		JLabel bg = new JLabel(new ImageIcon("img/bg.jpg"));
		//在背景层中添加背景图
		backLayer.add(bg);
		
		//把背景层添加到层叠容器的较底层
		layeredPane.add(backLayer,new Integer(0));
		
		//----在层叠容器中增加前景层----
		//创建前景层对象
		frontLayer = new JPanel();
		//创建CardLayout布局器对象，水平、垂直间距为0
		cardLayout = new CardLayout(0,0);
		//将前景层的布局器设为CardLayout
		frontLayer.setLayout(cardLayout);
		//将前景层的背景色设为透明（这样才能看到背景层）
		frontLayer.setOpaque(false);
		//设置前景层大小（同窗口）
		frontLayer.setSize(width,height);
		//设置前景层位置（相对于窗口左上角）
		frontLayer.setLocation(0,0);
		
		//把前景层添加到层叠容器的较顶层
		layeredPane.add(frontLayer,new Integer(1));
	}
	
	/**
	*登录界面
	* 
	* 本案例中，前景界面层面使用了CardLayout，是希望做到
	* 调用对应的方法时，把对应的层面调到最顶层即可；另外，
	* 每个层面第一次调用时，则初始化，后面的调用就可以直
	* 接把已初始化的层面调出并把一些必须的组件重置即可；
	*/
	//登录层容器
	public JPanel loginPane = null;
	//登录卡号输入框
	public JTextField loginCodeInput = null;
	//登录密码输入框
	public JPasswordField loginPassInput = null;
	//登录提示信息
	public JLabel loginTipsLable = null;
	
	public void showLogin(){
		if(loginPane == null){
			//-----登录层未初始化时--------
			
			//创建登录层容器对象
			loginPane = new JPanel();
			//把登录层的背景色变成透明
			loginPane.setOpaque(false);
			
				//-----往登录层容器中添加组件----
				//创建一个垂直BOX容器
				Box loginBox = Box.createVerticalBox();
				
				//在垂直Box中添加180高度的距离
				loginBox.add(Box.createVerticalStrut(180));
				
				//创建一个欢迎信息容器
				JPanel welcome_panel =  new JPanel();
					//把欢迎信息容器的背景色设为透明
					welcome_panel.setOpaque(false);
					//创建欢迎信息“欢迎使用海阁银行”
					JLabel welcome_lable = new JLabel("欢迎使用海阁银行");
					//设置信息字体
					welcome_lable.setForeground(Color.WHITE);
					welcome_lable.setFont(new Font("微软雅黑", Font.PLAIN, 30));
				//把欢迎信息添加到欢迎信息容器中
				welcome_panel.add(welcome_lable);
				
				//在垂直Box中添加30高度的距离
				loginBox.add(Box.createVerticalStrut(30));
				
				//创建一个卡号输入容器
				JPanel code_panel = new JPanel();
					//把卡号输入容器的背景色设为透明
					code_panel.setOpaque(false);
					//创建提示输入卡号“请输入卡号：”
					JLabel code_lable = new JLabel("请输入卡号：");
					//设置信息字体
					code_lable.setForeground(Color.WHITE);
					code_lable.setFont(new Font("微软雅黑",Font.PLAIN,25));
					//把提示输入卡号信息添加到卡号输入容器中
					code_panel.add(code_lable);
					//创建卡号输入框
					loginCodeInput = new JTextField(10);
					//设置卡号输入框字体
					loginCodeInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
				//把卡号输入框添加到卡号输入容器中
				code_panel.add(loginCodeInput);
				//把卡号输入容器添加到垂直Box容器中
				loginBox.add(code_panel);
				
				//在垂直Box中添加20高度的距离
				loginBox.add(Box.createVerticalStrut(20));
				
				//类似于卡号输入块，创建密码输入块
				JPanel pass_panel = new JPanel();
					pass_panel.setOpaque(false);
					JLabel pass_lable = new JLabel("请输入密码：");
					pass_lable.setForeground(Color.WHITE);
					pass_lable.setFont(new Font("微软雅黑",Font.PLAIN,25));
					pass_panel.add(pass_lable);
					loginPassInput = new JPasswordField(10);
					loginPassInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
				pass_panel.add(loginPassInput);
				loginBox.add(pass_panel);
				
				//在垂直Box中添加30高度的距离
				loginBox.add(Box.createVerticalStrut(30));
				
				//创建按钮容器
				JPanel btn_panel = new JPanel();
				btn_panel.setOpaque(false);
					//创建登录按钮并设置字体
					JButton login_btn = new JButton("登 录");
					login_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
					//把登录按钮添加到按钮容器中
					btn_panel.add(login_btn);
					
					//创建重置按钮并设置字体
					JButton reset_btn = new JButton("重 置");
					reset_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
				//把登录按钮添加到按钮容器中
				btn_panel.add(reset_btn);
				//把按钮容器添加到垂直BOX容器中
				loginBox.add(btn_panel);
				
				//在垂直Box中添加10高度的距离
				loginBox.add(Box.createVerticalStrut(10));
				
				//创建登录提示信息容器
				JPanel tips_panel = new JPanel();
				tips_panel.setOpaque(false);
					//创建登录信息对象并设置字体（默认无提示文字）
					loginTipsLable = new JLabel("");
					loginTipsLable.setForeground(new Color(238,32,32));
					loginTipsLable.setFont(new Font("微软雅黑",Font.PLAIN,20));
				//把登录提示信息添加到登录提示容器中
				tips_panel.add(loginTipsLable);
				//把登录提示容器添加到垂直BOX容器中
				loginBox.add(tips_panel);
			
			//把垂直BOX容器添加到登录层容器中
			loginPane.add(loginBox);
			
			//-----显示登录层-----
			//把登录层添加到前景层容器中
			frontLayer.add("loginPane",loginPane);
			//使登录层在前景层容器置于顶层显示
			cardLayout.show(frontLayer,"loginPane");
			//“刷新”前景层使其可视化
			frontLayer.validate();
			//使卡号输入框获取焦点（方便直接输入）
			loginCodeInput.requestFocus();
			
			//-----监听重置按钮事件-----
			reset_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//清空卡号输入框、密码输入框和提示信息
					loginCodeInput.setText("");
					loginPassInput.setText("");
					loginTipsLable.setText("");
					//使卡号输入框获取焦点
					loginCodeInput.requestFocus();
				}
			});
			
			//-----监听登录按钮事件-----
			login_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//获取用户输入的卡号和密码
					String code_str = loginCodeInput.getText();
					String pass_str = new String(loginPassInput.getPassword());
					//判断输入是否为空，为空则提示
					if("".equals(code_str)){
						loginTipsLable.setText("卡号不能为空！");
						loginCodeInput.requestFocus();
					}else if("".equals(pass_str)){
						loginTipsLable.setText("密码不能为空！");
						loginTipsLable.requestFocus();
					}else{
						//若卡号和密码输入都不为空，调用业务对象的登录业务方法并返回结果
						int login_rtn = bo.doLogin(code_str,Integer.valueOf(pass_str));
						
						if(login_rtn == -1){
							//返回-1，表示登陆成功，显示主菜单界面（未实现）
							showMenu();
						}else if(login_rtn == 3){
							//返回3，表示错误已经超过3次，显示吞卡提示界面（未完成）
							showTunka();
						}else{
							//返回非-1且非3,表示错误但未超过3次机会，提示输入错误信息
							loginCodeInput.setText("");
							loginPassInput.setText("");
							loginTipsLable.setText("卡号或密码错误，请重新输入，您还有"+(3-login_rtn)+"次机会！");
							loginCodeInput.requestFocus();
							
						}
					}
				}
			});
		}else{
			// -----登录层已初始化时------
			// 使登录层在前景层容器置于顶层显示
			cardLayout.show(frontLayer,"loginPane");
			// 重置
			loginCodeInput.setText("");
			loginPassInput.setText("");
			loginTipsLable.setText("");
			loginCodeInput.requestFocus();
		}
		
	}
	
	/**
	*吞卡提示界面
	*/
	public JPanel tunkaPane = null;
	
	public void showTunka(){
		if(tunkaPane == null){
			//------吞卡提示界面层未初始化时--------
			tunkaPane =  new JPanel();
			tunkaPane.setOpaque(false);
			
			//-----往吞卡提示界面层容器中添加组件--------
			Box tunkaBox =  Box.createVerticalBox();
				
				tunkaBox.add(Box.createVerticalStrut(180));
				
				JPanel tunka_panel = new JPanel();
				tunka_panel.setOpaque(false);
					JLabel tunka_label = new JLabel("您已超过3次输入机会，系统吞卡，请联系客服！");
					tunka_label.setForeground(Color.WHITE);
					tunka_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
				tunka_panel.add(tunka_label);
				tunkaBox.add(tunka_panel);
				
				tunkaBox.add(Box.createVerticalStrut(30));
				
				JPanel btn_panel = new JPanel();
				btn_panel.setOpaque(false);
					JButton tunka_btn = new JButton("确 定");
					tunka_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
				btn_panel.add(tunka_btn);
				tunkaBox.add(btn_panel);

				tunkaPane.add(tunkaBox);
				
				//------显示吞卡提示界面------
				frontLayer.add("tunkaPane",tunkaPane);
				cardLayout.show(frontLayer,"tunkaPane");
				frontLayer.validate();
				
				//------监听确定按钮事件------
				tunka_btn.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent ae){
						//退出
						quit();
					}
				});
		}else{
			//---------吞卡提示界面已经初始化时-------
			cardLayout.show(frontLayer,"tunkaPane");
		}
	}
	
	/**
	*主菜单界面
	*/
	// 主菜单界面层容器
	public JPanel menuPane = null;
	
	public void showMenu(){
		if(menuPane == null){
			//------主菜单界面未初始化时------
			menuPane = new JPanel();
			menuPane.setOpaque(false);
			//将主菜单界面层的布局器设为BorderLayout ？？？
			menuPane.setLayout(new BorderLayout());
			
			//-----往主菜单界面层容器添加组件-----
			//头部信息提示快，位于BorderLayout北区
			Box tipsBox = Box.createVerticalBox();
			menuPane.add(tipsBox,BorderLayout.NORTH);
			
			tipsBox.add(Box.createVerticalStrut(150));
			
			JLabel tips_label = new JLabel("请选择您需要的服务");
			tips_label.setForeground(Color.WHITE);
				tips_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
				//水平方向居中
				tips_label.setAlignmentX(Component.CENTER_ALIGNMENT);
			tipsBox.add(tips_label);
			
			//左栏按钮块，位于BorderLayout 西区
			Box menuLeft =  Box.createVerticalBox();
			menuPane.add(menuLeft,BorderLayout.WEST);
			
			menuLeft.add(Box.createVerticalStrut(50));
			
			JButton chaxun_btn = new JButton("查询余额");
			chaxun_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
			menuLeft.add(chaxun_btn);
			
			menuLeft.add(Box.createVerticalStrut(100));
			
			JButton cunkuan_btn = new JButton("存 款");
			cunkuan_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
			menuLeft.add(cunkuan_btn);
			
			menuLeft.add(Box.createVerticalStrut(100));
			
			JButton qukuan_btn = new JButton("取 款");
			qukuan_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
			menuLeft.add(qukuan_btn);
			
			//右栏按钮块，位于BorderLayout 东区
			Box menuRight =  Box.createVerticalBox();
			menuPane.add(menuRight,BorderLayout.EAST);
			
			menuRight.add(Box.createVerticalStrut(50));
			
			JButton xiugai_btn = new JButton("修改密码");
			xiugai_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
			//水平方向向右对齐
			xiugai_btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
			menuRight.add(xiugai_btn);
			
			menuRight.add(Box.createVerticalStrut(100));
			
			JButton zhuanzhang_btn = new JButton("转账");
			zhuanzhang_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
			//水平方向向右对齐
			zhuanzhang_btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
			menuRight.add(zhuanzhang_btn);
			
			menuRight.add(Box.createVerticalStrut(100));
			
			JButton quit_btn = new JButton("退 卡");
			quit_btn.setFont(new Font("微软雅黑",Font.PLAIN,25));
			//水平方向向右对齐
			quit_btn.setAlignmentX(Component.RIGHT_ALIGNMENT);
			menuRight.add(quit_btn);
			
			//-----显示主菜单界面层-----
			frontLayer.add("menuPane",menuPane);
			cardLayout.show(frontLayer,"menuPane");
			frontLayer.validate();
			
			//-----监听各按钮事件------
			chaxun_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//查询
					showChaxun();
				}
			});
			
			cunkuan_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//存款
					showCunkuan();
				}
			});
			
			qukuan_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//取款
					showQukuan();
				}
			});
			
			xiugai_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//修改密码
					showXiugai();
				}
			});
			
			zhuanzhang_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//修改密码
					showZhuanzhang();
				}
			});
			
			
			quit_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//退卡
					quit();
				}
			});
		}else{
			//--------主菜单界面层已经初始化时----------
			cardLayout.show(frontLayer,"menuPane");
		}
	}
	
	/**
	*查询界面
	*/
	public JPanel chaxunPane = null;
	
	//余额显示信息
	public JLabel chaxun_label = null;
	
	public void showChaxun(){
			//每一次都要初始化，刷新money的值
			chaxunPane = new JPanel();
			chaxunPane.setOpaque(false);
			
			//-------往查询界面容器中添加组件----
			Box chaxunBox = Box.createVerticalBox();
			
			chaxunBox.add(Box.createVerticalStrut(180));
			
			JPanel chaxun_panel =  new JPanel();
			chaxun_panel.setOpaque(false);
				//从bo中获得返回的余额
				double money_rtn = bo.doChaxun();
				//余额显示
				chaxun_label = new JLabel("您的账户余额为："+ money_rtn +"元");
				chaxun_label.setForeground(Color.WHITE);
				chaxun_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
			chaxun_panel.add(chaxun_label);
			chaxunBox.add(chaxun_panel);
			
			chaxunBox.add(Box.createVerticalStrut(30));
			
			//返回按钮
			JPanel btn_panel = new JPanel();
			btn_panel.setOpaque(false);
				JButton rtn_btn = new JButton("返 回");
				rtn_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
			btn_panel.add(rtn_btn);
			chaxunBox.add(btn_panel);
			
			chaxunPane.add(chaxunBox);
			
			//--------显示查询余额界面-----
			frontLayer.add("chaxunPane",chaxunPane);
			cardLayout.show(frontLayer,"chaxunPane");
			frontLayer.validate();
			
			//--------监听返回按钮事件------
			rtn_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//返回主菜单
					showMenu();
				}
			});
	}
	
	/**
	*存款界面
	*/
	//存款层容器
	public JPanel cunkuanPane = null;
	//存款金额输入款
	public JTextField cunkuanInput = null;
	//金额提示信息
	public JLabel cunkuanTipsLabel = null;
	
	public void showCunkuan(){
		if(cunkuanPane == null){
			//-----存款层未初始化时-----
			//创建存款层容器对象
			cunkuanPane = new JPanel();
			cunkuanPane.setOpaque(false);
			
			//-----往存款层容器中添加组件-----
			Box cunkuanBox = Box.createVerticalBox();
			
			cunkuanBox.add(Box.createVerticalStrut(180));
			
			//创建一个金额输入容器
			JPanel cunkuan_panel = new JPanel();
			cunkuan_panel.setOpaque(false);
				JLabel cunkuan_label = new JLabel("请输入存款金额：");
				cunkuan_label.setForeground(Color.WHITE);
				cunkuan_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
				cunkuan_panel.add(cunkuan_label);
				
				//创建金额输入框
				cunkuanInput = new JTextField(10);
				cunkuanInput.setFont(new Font("微软雅黑",Font.PLAIN,30));
				cunkuan_panel.add(cunkuanInput);
			cunkuanBox.add(cunkuan_panel);
			
			cunkuanBox.add(Box.createVerticalStrut(20));
			
			//创建按钮容器
			JPanel btn_panel = new JPanel();
				btn_panel.setOpaque(false);
					//创建确认按钮并设置字体
					JButton ok_btn = new JButton("确 认");
					ok_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
					//把确认按钮添加到按钮容器中
					btn_panel.add(ok_btn);
					
					//创建返回按钮并设置字体
					JButton rtn_btn = new JButton("返 回");
					rtn_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
				//把登录按钮添加到按钮容器中
				btn_panel.add(rtn_btn);
				//把按钮容器添加到垂直BOX容器中
			cunkuanBox.add(btn_panel);
			
			cunkuanBox.add(Box.createVerticalStrut(10));
			
			//创建存款金额提示容器
			JPanel tips_panel = new JPanel();
			tips_panel.setOpaque(false);
				
				//创建存款信息对象并设置字体（默认无提示文字）
				cunkuanTipsLabel =  new JLabel("");
				cunkuanTipsLabel.setForeground(new Color(238,32,32));
				cunkuanTipsLabel.setFont(new Font("微软雅黑",Font.PLAIN,20));
				tips_panel.add(cunkuanTipsLabel);
			cunkuanBox.add(tips_panel);
			
			cunkuanPane.add(cunkuanBox);
			
			//-----显示存款界面-----
			frontLayer.add("cunkuanPane",cunkuanPane);
			cardLayout.show(frontLayer,"cunkuanPane");
			frontLayer.validate();
			
			//获取焦点
			cunkuanInput.requestFocus();
			
			//------监听返回按钮事件------
			rtn_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//返回主菜单
					showMenu();
				}
			});
			
			//------监听确认按钮事件------
			ok_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					String cunkuan_str = cunkuanInput.getText();
					double cunkuan_double = Double.parseDouble(cunkuan_str);//将str变成double
					
					int rtn = bo.doCunkuan(cunkuan_double);
					//判断是否为整百
					if(rtn == 0){
						cunkuanTipsLabel.setText("存款不为整百！");
					}else{
						showCunkuanSuccess();
					}
				}
			});
		}else{
			//--------存款界面层已经初始化---------
			cardLayout.show(frontLayer,"cunkuanPane");
			cunkuanTipsLabel.setText("");
			cunkuanInput.setText("");
			cunkuanInput.requestFocus();
		}
	}
	
	/**
	*存款成功提示界面
	*/
	public JPanel cksuccessPane = null;
	
	public void showCunkuanSuccess(){
		if(cksuccessPane == null){
			//--------存款成功提示界面未初始化时--------
			cksuccessPane = new JPanel();
			cksuccessPane.setOpaque(false);
			
			//-------往存款成功提示界面容器中添加组件----
			Box cksuccessBox = Box.createVerticalBox();
			
			cksuccessBox.add(Box.createVerticalStrut(180));
			
			JPanel cksuccess_panel =  new JPanel();
			cksuccess_panel.setOpaque(false);
				//存款成功提示界面
				JLabel cksuccess_label = new JLabel("存款成功！");
				cksuccess_label.setForeground(Color.WHITE);
				cksuccess_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
			cksuccess_panel.add(cksuccess_label);
			cksuccessBox.add(cksuccess_panel);
			
			cksuccessBox.add(Box.createVerticalStrut(30));
			
			//返回按钮
			JPanel btn_panel = new JPanel();
			btn_panel.setOpaque(false);
				JButton rtn_btn = new JButton("返 回");
				rtn_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
			btn_panel.add(rtn_btn);
			cksuccessBox.add(btn_panel);
			
			cksuccessPane.add(cksuccessBox);
			
			//--------显示存款成功提示界面界面-----
			frontLayer.add("cksuccessPane",cksuccessPane);
			cardLayout.show(frontLayer,"cksuccessPane");
			frontLayer.validate();
			
			//--------监听返回按钮事件------
			rtn_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//返回主菜单
					showMenu();
				}
			});
			
		}else{
			//--------存款成功提示界面层已经初始化---------
			cardLayout.show(frontLayer,"cksuccessPane");
		}
	}
	
	/**
	*取款界面
	*/
	public JPanel qukuanPane = null;
	public JTextField qukuanInput = null;
	public JLabel qukuanTipsLabel = null;
	
	public void showQukuan(){
		if(qukuanPane == null){
			//------取款层未初始化时-----
			qukuanPane = new JPanel();
			qukuanPane.setOpaque(false);
			
			Box qukuanBox = Box.createVerticalBox();
			
			qukuanBox.add(Box.createVerticalStrut(180));
			
			JPanel qukuan_panel = new JPanel();
			qukuan_panel.setOpaque(false);
				JLabel qukuan_label = new JLabel("请输入取款金额：");
				qukuan_label.setForeground(Color.WHITE);
				qukuan_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
				qukuan_panel.add(qukuan_label);
				
				//创建金额输入框
				qukuanInput = new JTextField(10);
				qukuanInput.setFont(new Font("微软雅黑",Font.PLAIN,30));
				qukuan_panel.add(qukuanInput);
			qukuanBox.add(qukuan_panel);
			
			qukuanBox.add(Box.createVerticalStrut(20));
			
			//创建按钮容器
			JPanel btn_panel = new JPanel();
				btn_panel.setOpaque(false);
					//创建确认按钮并设置字体
					JButton ok_btn = new JButton("确 认");
					ok_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
					//把确认按钮添加到按钮容器中
					btn_panel.add(ok_btn);
					
					//创建返回按钮并设置字体
					JButton rtn_btn = new JButton("返 回");
					rtn_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
				//把登录按钮添加到按钮容器中
				btn_panel.add(rtn_btn);
				//把按钮容器添加到垂直BOX容器中
			qukuanBox.add(btn_panel);
			
			qukuanBox.add(Box.createVerticalStrut(10));
			
			//创建取款金额提示容器
			JPanel tips_panel = new JPanel();
			tips_panel.setOpaque(false);
				
				//创建存款信息对象并设置字体（默认无提示文字）
				qukuanTipsLabel =  new JLabel("");
				qukuanTipsLabel.setForeground(new Color(238,32,32));
				qukuanTipsLabel.setFont(new Font("微软雅黑",Font.PLAIN,20));
				tips_panel.add(qukuanTipsLabel);
			qukuanBox.add(tips_panel);
			
			qukuanPane.add(qukuanBox);
			
			//-----显示取款界面-----
			frontLayer.add("qukuanPane",qukuanPane);
			cardLayout.show(frontLayer,"qukuanPane");
			frontLayer.validate();
			
			//获取焦点
			qukuanInput.requestFocus();
			
			//------监听返回按钮事件------
			rtn_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//返回主菜单
					showMenu();
				}
			});
			
			//------监听确认按钮事件------
			ok_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					String qukuan_str = qukuanInput.getText();
					double qukuan_double = Double.parseDouble(qukuan_str);//将str变成double
					double money_rtn=bo.doChaxun();//得到现在的余额
					
					int rtn = bo.doQukuan(qukuan_double);
					//判断是否为整百以及是否超过余额
					if(rtn == -1){
						qukuanTipsLabel.setText("取款不为整百！");
					}else if(rtn == 0){
						qukuanTipsLabel.setText("余额不足！");
					}else{
						showQukuanSuccess();
					}
					
				}
			});
		}else{
			//--------取款界面层已经初始化---------
			cardLayout.show(frontLayer,"qukuanPane");
			qukuanTipsLabel.setText("");
			qukuanInput.setText("");
			qukuanInput.requestFocus();
		}
	}
	
	/**
	*取款成功提示界面
	*/
	public JPanel qksuccessPane = null;
	
	public void showQukuanSuccess(){
		if(qksuccessPane == null){
			//--------取款成功提示界面未初始化时--------
			qksuccessPane = new JPanel();
			qksuccessPane.setOpaque(false);
			
			//-------往取款成功提示界面容器中添加组件----
			Box qksuccessBox = Box.createVerticalBox();
			
			qksuccessBox.add(Box.createVerticalStrut(180));
			
			JPanel qksuccess_panel =  new JPanel();
			qksuccess_panel.setOpaque(false);
				JLabel qksuccess_label = new JLabel("取款成功！");
				qksuccess_label.setForeground(Color.WHITE);
				qksuccess_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
			qksuccess_panel.add(qksuccess_label);
			qksuccessBox.add(qksuccess_panel);
			
			qksuccessBox.add(Box.createVerticalStrut(30));
			
			//返回按钮
			JPanel btn_panel = new JPanel();
			btn_panel.setOpaque(false);
				JButton rtn_btn = new JButton("返 回");
				rtn_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
			btn_panel.add(rtn_btn);
			qksuccessBox.add(btn_panel);
			
			qksuccessPane.add(qksuccessBox);
			
			//--------显示存款成功提示界面界面-----
			frontLayer.add("qksuccessPane",qksuccessPane);
			cardLayout.show(frontLayer,"qksuccessPane");
			frontLayer.validate();
			
			//--------监听返回按钮事件------
			rtn_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//返回主菜单
					showMenu();
				}
			});
			
		}else{
			//--------存款成功提示界面层已经初始化---------
			cardLayout.show(frontLayer,"qksuccessPane");
		}
	}
	
	/**
	*密码修改界面
	*/
	public JPanel xiugaiPane = null;
	public JTextField oldpasswordInput = null;
	public JTextField newpasswordInput = null;
	public JTextField renewpasswordInput = null;
	public JLabel xiugaiTipsLable = null ;
	
	public void showXiugai(){
		if(xiugaiPane == null){
			//-------修改密码层未初始化时------
			
			//修改层容器
			xiugaiPane = new JPanel();
			xiugaiPane.setOpaque(false);
				//往容器中添加组件
				Box xiugaiBox = Box.createVerticalBox();
				
				xiugaiBox.add(Box.createVerticalStrut(180));
				
				//原密码输入组件
				JPanel oldpassword_panel = new JPanel();
				oldpassword_panel.setOpaque(false);
					JLabel oldpassword_label = new JLabel("请输入原密码：");
					oldpassword_label.setForeground(Color.WHITE);
					oldpassword_label.setFont(new Font("微软雅黑",Font.PLAIN,25));
					oldpassword_panel.add(oldpassword_label);
					
					oldpasswordInput = new JTextField(10);
					oldpasswordInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
					oldpassword_panel.add(oldpasswordInput);
				xiugaiBox.add(oldpassword_panel);
				
				xiugaiBox.add(Box.createVerticalStrut(20));
				
				//新密码输入组件
				JPanel newpassword_panel = new JPanel();
				newpassword_panel.setOpaque(false);
					JLabel newpassword_label = new JLabel("请输入新密码：");
					newpassword_label.setForeground(Color.WHITE);
					newpassword_label.setFont(new Font("微软雅黑",Font.PLAIN,25));
					newpassword_panel.add(newpassword_label);
					
					newpasswordInput = new JTextField(10);
					newpasswordInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
					newpassword_panel.add(newpasswordInput);
				xiugaiBox.add(newpassword_panel);
				
				xiugaiBox.add(Box.createVerticalStrut(20));
				
				//确认新密码输入组件
				JPanel renewpassword_panel = new JPanel();
				renewpassword_panel.setOpaque(false);
					JLabel renewpassword_label = new JLabel("请确认新密码：");
					renewpassword_label.setForeground(Color.WHITE);
					renewpassword_label.setFont(new Font("微软雅黑",Font.PLAIN,25));
					renewpassword_panel.add(renewpassword_label);
					
					renewpasswordInput = new JTextField(10);
					renewpasswordInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
					renewpassword_panel.add(renewpasswordInput);
				xiugaiBox.add(renewpassword_panel);
				
				xiugaiBox.add(Box.createVerticalStrut(20));
				
				//创建按钮容器
				JPanel btn_panel = new JPanel();
				btn_panel.setOpaque(false);
					JButton ok_btn = new JButton("确 认");
					ok_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
					btn_panel.add(ok_btn);
					
					JButton rtn_btn = new JButton("返 回");
					rtn_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
					btn_panel.add(rtn_btn);
				xiugaiBox.add(btn_panel);
				
				xiugaiBox.add(Box.createVerticalStrut(20));
				
				//创建修改密码提示容器
				JPanel tips_panel = new JPanel();
				tips_panel.setOpaque(false);
					xiugaiTipsLable =  new JLabel("");
					xiugaiTipsLable.setForeground(new Color(238,32,32));
					xiugaiTipsLable.setFont(new Font("微软雅黑",Font.PLAIN,20));
					tips_panel.add(xiugaiTipsLable);
				xiugaiBox.add(tips_panel);
				
			xiugaiPane.add(xiugaiBox);
			
			//------显示修改界面-------
			frontLayer.add("xiugaiPane",xiugaiPane);
			cardLayout.show(frontLayer,"xiugaiPane");
			frontLayer.validate();
			
			//获取焦点
			oldpasswordInput.requestFocus();
			
			//------监听返回按钮事件------
			rtn_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//返回主菜单
					showMenu();
				}
			});
			
			//------监听确认按钮事件------
			ok_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//获取三个输入值
					String oldpassword_str = oldpasswordInput.getText();
					String newpassword_str = newpasswordInput.getText();
					String renewpassword_str = renewpasswordInput.getText();
					
					//判断输入是否为空，为空则提示
					if( "".equals(oldpassword_str)){
						xiugaiTipsLable.setText("原密码为必填！");
					}else if("".equals(newpassword_str)){
						xiugaiTipsLable.setText("新密码为必填！");
					}else if("".equals(renewpassword_str)){
						xiugaiTipsLable.setText("确认密码为必填！");
					}else{
						int oldpassword_int = Integer.parseInt(oldpassword_str);
						int newpassword_int = Integer.parseInt(newpassword_str);
						int renewpassword_int = Integer.parseInt(renewpassword_str);
						
						int xiugai_rtn = bo.doXiugai(oldpassword_int,newpassword_int,renewpassword_int);
						
						if(xiugai_rtn == 1){
							//原密码不正确
							xiugaiTipsLable.setText("原密码错误！");
						}else if(xiugai_rtn == 2){
							//两次密码输入不一致
							xiugaiTipsLable.setText("两次密码输入不一致！");
						}else if(xiugai_rtn == 3){
							//修改成功
							showXiugaiSuccess();
						}
					}
				}
			});
			
		 }else{
			cardLayout.show(frontLayer,"xiugaiPane");
			xiugaiTipsLable.setText("");
			oldpasswordInput.setText("");
			newpasswordInput.setText("");
			renewpasswordInput.setText("");
			oldpasswordInput.requestFocus();
		}
	}
	
	/**
	*修改密码成功提示界面
	*/
	public JPanel xgsuccessPane = null;
	
	public void showXiugaiSuccess(){
		if(xgsuccessPane == null){
			//--------取款成功提示界面未初始化时--------
			xgsuccessPane = new JPanel();
			xgsuccessPane.setOpaque(false);
			
			//-------往取款成功提示界面容器中添加组件----
			Box xgsuccessBox = Box.createVerticalBox();
			
			xgsuccessBox.add(Box.createVerticalStrut(180));
			
			JPanel xgsuccess_panel =  new JPanel();
			xgsuccess_panel.setOpaque(false);
				JLabel xgsuccess_label = new JLabel("修改密码成功！");
				xgsuccess_label.setForeground(Color.WHITE);
				xgsuccess_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
			xgsuccess_panel.add(xgsuccess_label);
			xgsuccessBox.add(xgsuccess_panel);
			
			xgsuccessBox.add(Box.createVerticalStrut(30));
			
			//返回按钮
			JPanel btn_panel = new JPanel();
			btn_panel.setOpaque(false);
				JButton rtn_btn = new JButton("返 回");
				rtn_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
			btn_panel.add(rtn_btn);
			xgsuccessBox.add(btn_panel);
			
			xgsuccessPane.add(xgsuccessBox);
			
			//--------显示存款成功提示界面界面-----
			frontLayer.add("xgsuccessPane",xgsuccessPane);
			cardLayout.show(frontLayer,"xgsuccessPane");
			frontLayer.validate();
			
			//--------监听返回按钮事件------
			rtn_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//返回主菜单
					showMenu();
				}
			});
			
		}else{
			//--------存款成功提示界面层已经初始化---------
			cardLayout.show(frontLayer,"xgsuccessPane");
		}
	}
	
	/**
	*转帐界面
	*/
	public JPanel zhuanzhuangPane = null;
	public JTextField codeInput = null;
	public JTextField moneyInput = null;
	public JLabel zhuanzhangTipsLable = null ;
	
	public void showZhuanzhang(){
		if(zhuanzhuangPane == null){
			//--------未初始化时--------
			
			//修改层容器
			zhuanzhuangPane = new JPanel();
			zhuanzhuangPane.setOpaque(false);
				//往容器中添加组件
				Box zhuanzhuangBox = Box.createVerticalBox();
				
				zhuanzhuangBox.add(Box.createVerticalStrut(180));
				
				//原密码输入组件
				JPanel codeinput_panel = new JPanel();
				codeinput_panel.setOpaque(false);
					JLabel codeinput_label = new JLabel("请输入账号：");
					codeinput_label.setForeground(Color.WHITE);
					codeinput_label.setFont(new Font("微软雅黑",Font.PLAIN,25));
					codeinput_panel.add(codeinput_label);
					
					codeInput = new JTextField(10);
					codeInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
					codeinput_panel.add(codeInput);
				zhuanzhuangBox.add(codeinput_panel);
				
				zhuanzhuangBox.add(Box.createVerticalStrut(20));
				
				//新密码输入组件
				JPanel moneyinput_panel = new JPanel();
				moneyinput_panel.setOpaque(false);
					JLabel moneyinput_label = new JLabel("请输入金额：");
					moneyinput_label.setForeground(Color.WHITE);
					moneyinput_label.setFont(new Font("微软雅黑",Font.PLAIN,25));
					moneyinput_panel.add(moneyinput_label);
					
					moneyInput = new JTextField(10);
					moneyInput.setFont(new Font("微软雅黑",Font.PLAIN,25));
					moneyinput_panel.add(moneyInput);
				zhuanzhuangBox.add(moneyinput_panel);
				
				zhuanzhuangBox.add(Box.createVerticalStrut(20));
				
				//创建按钮容器
				JPanel btn_panel = new JPanel();
				btn_panel.setOpaque(false);
					JButton ok_btn = new JButton("确 认");
					ok_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
					btn_panel.add(ok_btn);
					
					JButton rtn_btn = new JButton("返 回");
					rtn_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
					btn_panel.add(rtn_btn);
				zhuanzhuangBox.add(btn_panel);
				
				zhuanzhuangBox.add(Box.createVerticalStrut(20));
				
				//创建提示容器
				JPanel tips_panel = new JPanel();
				tips_panel.setOpaque(false);
					zhuanzhangTipsLable =  new JLabel("");
					zhuanzhangTipsLable.setForeground(new Color(238,32,32));
					zhuanzhangTipsLable.setFont(new Font("微软雅黑",Font.PLAIN,20));
					tips_panel.add(zhuanzhangTipsLable);
				zhuanzhuangBox.add(tips_panel);
				
			zhuanzhuangPane.add(zhuanzhuangBox);
			
			//------显示界面-------
			frontLayer.add("zhuanzhuangPane",zhuanzhuangPane);
			cardLayout.show(frontLayer,"zhuanzhuangPane");
			frontLayer.validate();
			
			//获取焦点
			codeInput.requestFocus();
			
			//------监听返回按钮事件------
			rtn_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//返回主菜单
					showMenu();
				}
			});
			
			//------监听确认按钮事件------
			ok_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//获取三个输入值
					String code_str = codeInput.getText();
					String money_str = moneyInput.getText();
					
					//判断输入是否为空，为空则提示
					if( "".equals(code_str)){
						zhuanzhangTipsLable.setText("账号为必填！");
					}else if("".equals(money_str)){
						zhuanzhangTipsLable.setText("金额为必填！");
					}else{
						double money_double = Double.parseDouble(money_str);//将str变成double
						int rtn = bo.doZhuanzhang(code_str,money_double);
						//判断并提示
						if(rtn == 1){
							zhuanzhangTipsLable.setText("输入账号与登陆账号相同!");
						}else if(rtn == 2){
							zhuanzhangTipsLable.setText("输入账号不存在!");
						}else if(rtn == 3){
							zhuanzhangTipsLable.setText("余额不足!");
						}else if(rtn == 4){
							zhuanzhangTipsLable.setText("输入金额不为整百!");
						}else if(rtn == 5){
							showZhuanzhangSuccess();
						}
					}
				}
			});
			
		}else{
			cardLayout.show(frontLayer,"zhuanzhuangPane");
			zhuanzhangTipsLable.setText("");
			codeInput.setText("");
			moneyInput.setText("");
			codeInput.requestFocus();
		}
	}
	
	/**
	*转帐成功提示界面
	*/
	public JPanel zzsuccessPane = null;
	
	public void showZhuanzhangSuccess(){
		if(zzsuccessPane == null){
			//--------未初始化时--------
			zzsuccessPane = new JPanel();
			zzsuccessPane.setOpaque(false);
			
			//-------往容器中添加组件----
			Box zzsuccessBox = Box.createVerticalBox();
			
			zzsuccessBox.add(Box.createVerticalStrut(180));
			
			JPanel zzsuccess_panel =  new JPanel();
			zzsuccess_panel.setOpaque(false);
				JLabel zzsuccess_label = new JLabel("转账成功！");
				zzsuccess_label.setForeground(Color.WHITE);
				zzsuccess_label.setFont(new Font("微软雅黑",Font.PLAIN,30));
			zzsuccess_panel.add(zzsuccess_label);
			zzsuccessBox.add(zzsuccess_panel);
			
			zzsuccessBox.add(Box.createVerticalStrut(30));
			
			//返回按钮
			JPanel btn_panel = new JPanel();
			btn_panel.setOpaque(false);
				JButton rtn_btn = new JButton("返 回");
				rtn_btn.setFont(new Font("微软雅黑",Font.PLAIN,15));
			btn_panel.add(rtn_btn);
			zzsuccessBox.add(btn_panel);
			
			zzsuccessPane.add(zzsuccessBox);
			
			//--------显示存款成功提示界面界面-----
			frontLayer.add("zzsuccessPane",zzsuccessPane);
			cardLayout.show(frontLayer,"zzsuccessPane");
			frontLayer.validate();
			
			//--------监听返回按钮事件------
			rtn_btn.addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent ae){
					//返回主菜单
					showMenu();
				}
			});
			
		}else{
			//--------存款成功提示界面层已经初始化---------
			cardLayout.show(frontLayer,"zzsuccessPane");
		}
	}
	
	
	/**
	*退卡
	*/
	public void quit(){
		//重新初始化业务对象
		initBO();
		//重新显示登录界面
		showLogin();
	}
}