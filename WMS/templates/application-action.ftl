
<#--${cnName}action-->
<bean id="${objectName}Action" class="${basePkg}.web.action.${className}Action" scope="prototype">
    <property name="${objectName}Service" ref="${objectName}Service"/>
</bean>