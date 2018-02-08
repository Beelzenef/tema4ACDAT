# tema4ACDAT

Ejercicios con JSON

## Tiempo desde JSON

Lectura de tiempo, temperatura, humedad... en diferentes ciudades. La lista se ha limitado a unas pocas localizaciones, que puedes elegir a través de un ArrayList. Málaga, Barcelona, Zaragoza, Bilbao... son las ciudades elegidas. El elemento de cada ciudad se muestra en una nueva Activity con los datos actualizados, gracias a [OpenWeatherMap](http://www.openweathermap.org/).

## Tiempo hacia JSON y XML

Lectura de tiempo, temperatura, humedad... en diferentes ciudades. Los resultados obtenidos se deben escribir en dos ficheros independientes, uno en formato XML y otro en formato JSON. Los datos se obtienen gracias a [OpenWeatherMap](http://www.openweathermap.org/).

#### Posibles mejoras

La escritura de ficheros se realiza con las ciudades, y no con los tramos de tiempo de una determinada ciudad.

## Cambio de divisas

Se ha recuperado la aplicación de temas anteriores para el cambio de divisas entre euros (EUR) y dólares estadounidenses (USD). Ahora el cambio se obtiene de forma remota de una página donde se listan todos los cambios de una moneda concreta con respecto a concurrencia de otras localizaciones.

Si no fuera posible conseguir el valor en remoto a través de JSON, se coge el valor por defecto entregado en la Clase Conversor.

Los datos son obtenidos del portal [floatRates, JSON feeds](http://www.floatrates.com/json-feeds.html).

## BiziZaragoza

Se ha recuperado la aplicación de BiziZaragoza, que anteriormente leíamos a través de su feed en XML, para hacerlo en esta ocasión en XML. Se ha utilizado un RecyclerView con un adapter personalizado, utilizando las instrucciones seguidas en clase para añadir el evento _onClick_ en cada elemento de la lista.

Los datos se han obtenido de [BiziZaragoza](https://www.zaragoza.es/sede/portal/datos-abiertos/servicio/catalogo/70), en el siguiente [fichero JSON](https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta.json).

#### Posibles mejoras

No se ha implementado Retrofit y GSON.

## Mostrando datos

Se muestra el listado y posterior visualización de elementos individuales de las bibliotecas que forman parte de la Xarxa de Biblioteques (red de bibliotecas) de Barcelona. Los enlaces y direcciones de correo electrónico están disponibles para lanzar o bien un navegador o un cliente de correo electrónico. Ideal para informarnos sobre bibliotecas en la ciudad para realizar actividades culturales.

Los datos se han obtenido de [datos.gob.es](http://datos.gob.es/es/catalogo/l02000008-bibliotecas-municipales), en el siguiente [fichero JSON](http://do.diba.cat/api/dataset/biblioteques/format/json).

#### Posibles mejores

Intento de implementar un _intent_ para realizar llamadas telefónicas, sin éxito.
