提示git速度办法
git config --global https.proxy 'socks5://127.0.0.1:1080'

ipconfig /flushdns


C:\Windows\System32\drivers\etc\hosts文件中添加

151.101.88.249  github.global.ssl.fastly.net
151.101.100.133 assets-cdn.github.com
192.30.253.113 www.github.com
151.101.16.133 avatars0.githubusercontent.com
151.101.100.133 avatars1.githubusercontent.com

git clone https://github.com/zjblovewl/hx.git copy仓库命令

Git的文件夹上传步骤
账户名zjblovewl@sina.com（zjblovewl：username）（www..1994：pwd）

进入到上传的目录然后用git init初始化一下

第二步git add。（添加到暂存区里面去）

第三步git commit -m'第一次提交'（提交）

第四步$ git remote add origin https://github.com/zjblovewl/workspace.git

若报起源已存在执行git remote rm origin

第五步若项目第一次上传，则不需要，若存在，在需要对比同步，否则后续不能提交
       git pull --rebase origin master（新的分支，本地不存在，可以git branch work）
	   
第六步git push -u origin master

	   git push -f若出现不一致，则强制推送，覆盖远程

输入用户名：zjblovewl
输入密码：www..1994至此提交成功

参考连接：
http://www.cnblogs.com/eedc/p/6168430.html
http://blog.csdn.net/qq_24489717/article/details/52556072


$ git add HttpClient.rar

$ git commit -m“20171019”

$ git remote add httpclient git@github.com:zjblovewl / workspace.git

$ git push -u httpclient master



git@github.com：zjblovewl / practise.git


