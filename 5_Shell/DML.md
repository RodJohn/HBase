
# 列操作

新增

    在列簇中插入数据:格式：put 表名，行键id，列簇名：列名，值
    put 'member','xiaoming','address:contry','china'
    
修改

    put 'member','xiaoming','info:age','26' 
    更新一条记录：给rowId重新put即可

删除

    删除id为xiaomiing的值的'info:age'字段
    delete 'member','xiaoming','info:age'
    
    删除整行
    deteleall 'member','xiaoming'

    将整张表清空：实际执行过程：hbase是先将表disable，然后drop，后重建表，来实现truncate的功能的
    truncate 'member'


