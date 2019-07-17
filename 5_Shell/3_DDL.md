
# 概述

    表结构包括命名空间、表、列族

# 命名空间

命名空间

    命名空间相当于库
    默认的命名空间就是default

新建

     create_namespace 'ns1'
     
列出所有命名空间

    list_namespace
    
删除

    drop_namespace 'ns1'   
        
# 表

创建

    格式： create 表名，列簇1，列簇2...列簇N
    create ‘member’,'member_id','address','info'

查看表描述

    describe 'member'

列出所有表

    list

删除
    
    删除一个表：先关闭，再删除
    
    disable 'member'
    drop 'member'
    

列出所有表
    
    list

    
# 列族    

    删除一个列簇：
    先关闭表，再更新列族，再打开表
    
    disable 'member'
    alter'member',NAME=>'member_id',METHOD=>'delete'
    enable 'member'
    