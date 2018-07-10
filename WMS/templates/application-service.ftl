<!-- ##############################模板生成的-->
<!--number.${cnName}${objectName}Service-->
<bean id="${objectName}Service" class="${basePkg}.web.service.impl.${className}ServiceImpl">
    <property name="${objectName}DAO" ref="${objectName}DAO"/>
</bean>