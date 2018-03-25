<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h3>${medicine.name}</h3>
<p>Описание препарата: ${medicine.description}</p>
<p>Цена препарата: ${medicine.price} бел.руб.</p>
<p>Количество упаковок в наличии: ${medicine.quantity}</p>
<p>Лекарственная группа: ${medicine.group.name}</p>
</body>
</html>
