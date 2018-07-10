<?xml version='1.0' encoding='utf-8'?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd">

<hibernate-mapping package="${basePkg}.domain">

    <class name="${className}" table="${objectName}">

        <!--1.主键-->
        <id name="id" column="id">
            <generator class="native"/>
        </id>
        <!--2.普通属性-->
        <#list propertyNames as propertyName>
            <property name="${propertyName}" column="${propertyName}"/>
        </#list>
        <!--3.关联属性-->

    </class>

</hibernate-mapping>