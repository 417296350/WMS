<!-- ##############################模板生成的-->
<#--定义${className}DAOImpl-->
<bean id="${objectName}DAO" class="${basePkg}.dao.impl.${className}DAOImpl">
    <property name="hibernateTemplate" ref="hibernateTemplate"/>
</bean>