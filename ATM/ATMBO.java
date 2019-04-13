import java.io.*;//导入IO包

public class ATMBO{

	//定义一个字符串String，属性名为code,未初始化
	public static String[] code = null;
	//定义一个整型int，属性名为password,未初始化
	public static int[] password = null;
	//定义一个浮点型double，属性名为money，未初始化
	public static double[] money = null;
	//定义一个整型int，属性名为current_index，存储当前账号的游标
	public int currrent_index = -1;
	//当前密码错误次数
	public int cs = 0;
	
	/**
	*	读取
	*/
	public void read(){
		FileReader fr = null;	//用于读取文件
		BufferedReader br = null;	//用于整行文本读取
		try{
			fr =  new FileReader("account.txt");
			br =  new BufferedReader(fr);	//将文本内容整个读取到缓存里面
			
			String line1 = br.readLine();	//整行文本读取，内容为linel的初始值
			code = line1.split(",");		//将linel的值按,划分为几个值赋值给code数组
			
			String line2 = br.readLine();	//处理password
			String[] password_temp = line2.split(",");
			password = new int[password_temp.length];	//确定password数组的大小
			for(int i=0; i<password_temp.length; i++){
				password[i] = Integer.valueOf(password_temp[i]);	//将string转成int
			}
			
			String line3 = br.readLine();	//处理money
			String[] money_temp = line3.split(",");
			money = new double[money_temp.length];
			for(int i=0; i<money_temp.length; i++){
				money[i] = Double.valueOf(money_temp[i]);
			}
			
		}catch(FileNotFoundException e){	//捕捉找不到文件异常
			System.out.println("找不到文件！");
		}catch(IOException e){				//捕捉IO异常
			System.out.println("IO异常！");	
		}finally{
			try{
				if(br != null){ br.close(); }
				if(fr != null){ fr.close(); }
			}catch(IOException e){
				System.out.println("IO异常!");
			}
		}
	}
	
	/**
	*	写入
	*/
	public void write(){
		FileWriter fw = null;	//用于写入文件
		BufferedWriter bw = null;	//用于逐行写入
		try{
			fw = new FileWriter("account.txt");
			bw = new BufferedWriter(fw);
			
			String line1 = "";	//拼接第一行数据code
			for(int i=0; i<code.length; i++){
				line1 =  line1 + code[i];
				if(i != code.length-1){
					line1 = line1 + ",";
				}
			}
			bw.write(line1);	//写入第一行数据
			bw.newLine();		//换行
			
			String line2 = "";	//拼接第二行数据code
			for(int i=0; i<password.length; i++){
				line2 =  line2 + password[i];
				if(i != password.length-1){
					line2 = line2 + ",";
				}
			}
			bw.write(line2);	//写入第二行数据
			bw.newLine();		//换行
			
			String line3 = "";	//拼接第三行数据code
			for(int i=0; i<money.length; i++){
				line3 =  line3 + money[i];
				if(i != money.length-1){
					line3 = line3 + ",";
				}
			}
			bw.write(line3);	//写入第二行数据
		}catch(FileNotFoundException e){	//捕捉找不到文件异常
			System.out.println("找不到文件！");
		}catch(IOException e){				//捕捉IO异常
			System.out.println("IO异常！");	
		}finally{
			try{
				if(bw != null){ bw.close(); }
				if(fw != null){ fw.close(); }
			}catch(IOException e){
				System.out.println("IO异常!");
			}
		}
	}
	
	
	/**
	*	登录业务
	*/
	public int doLogin(String code_input, int password_input){
		//读取account.txt中的数据到数组中
		read();
		//定义一个循环语句，查看卡号
		for(int i=0; i<code.length; i++){
			if(code_input.equals(code[i])){
				//判断用户是否存在，记录下当前卡号的游标
				currrent_index = i;
				break;
			}
		}
		//判断current_index是否为-1
		if(currrent_index == -1){
			//错误次数+1
			cs++;
			return cs;
		}else if(password_input != password[currrent_index]){
			//判断密码是否正确，错误次数+1
			cs++;
			return cs;
		}else{
			//卡号存在并且密码正确
			return -1;
		}
		

	}
	
	/**
	*	查询业务
	*/
	public double doChaxun(){
		//读取account.txt中的数据到数组中
		read();
		return money[currrent_index];
	}
	
	/**
	*	存款业务
	*/
	public int doCunkuan(double money_input){
		//读取account.txt中的数据到数组中
		read();
		//判断是否整百
		if(money_input%100 != 0){
			//如果不整白，返回整型 0
			return 0;
		}else{
			money[currrent_index] = money[currrent_index]  + money_input; 
			//把修改的数据写入硬盘文件account.txt
			write();
			return 1;
		}
		
	}
	
	/**
	*	取款业务
	*/
	public int doQukuan(double money_input){
		read();
		//判断是否整百
		if(money_input%100 != 0){
			return -1;
		}else if(money_input > money[currrent_index]){
			//超出余额
			return 0;
		}else{
			//取出成功
			money[currrent_index] = money[currrent_index] - money_input; 
			write();
			return 1;
		}

	}
	
	/**
	*	密码修改业务
	*/
	public int a = 0;
	public int doXiugai(int oldpassword_input,int newpassword_input,int renewpasswor_input ){
		read();
		if(oldpassword_input == password[currrent_index]){
			if(newpassword_input == renewpasswor_input){
				a = 3;//修改密码成功
				password[currrent_index] = newpassword_input;
				write();
			}else{
				a = 2;//两次输入密码不相同
			}
		}else{
			a = 1;//原密码错误
		}
		
		return a;
	}
	
	/**
	*	转账业务
	*/
	public int doZhuanzhang(String code_input,double money_input){
		read();
		if(code_input == code[currrent_index]){ //输入账号与登陆账号相同，报错
			return 1;
		}else{
			//输入账号与登陆账号不同，继续判断输入账号是否存在
			for(int i=0; i<code.length; i++){
					if(code_input.equals(code[i])){
						int code_zz = i;//标记转账对象游标
						//若输入账号存在。继续判断
						if(money_input > money[currrent_index]){
							//超出本账户余额
							return 3;
						}else if(money_input%100 != 0){
							//不是整额
							return 4;
						}else{
							//转账操作
							money[currrent_index] = money[currrent_index] - money_input;
							money[code_zz] = money [code_zz] + money_input;
							write();
							return 5;
						}
					
				}
			}
			return 2; //若输入账号不存在，报错
		}
	}
	
}