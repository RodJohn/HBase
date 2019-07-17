
# 概述

    表结构包括表和列族
    
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

    5、删除一个列簇：先关闭，再更新，再打开
    
    disable 'member'
    
    alter'member',NAME=>'member_id',METHOD=>'delete'
    
    enable 'member'
    