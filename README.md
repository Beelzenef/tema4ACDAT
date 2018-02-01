# tema4ACDAT

Ejercicios con JSON

## Tiempo en JSON
## Tiempo en (JSON || XML)

## Cambio de divisas

Se ha recuperado la aplicación de temas anteriores para el cambio de divisas entre euros (EUR) y dólares estadounidenses (USD). Ahora el cambio se obtiene de forma remota de una página donde se listan todos los cambios de una moneda concreta con respecto a concurrencia de otras localizaciones.

Los datos son obtenidos del portal [floatRates, JSON feeds](http://www.floatrates.com/json-feeds.html).

## BiziZaragoza

Se ha recuperado la aplicación de BiziZaragoza, que anteriormente leíamos a través de su feed en XML, para hacerlo en esta ocasión en XML.

Se ha utilizado un ListView para mayor velocidad en el desarrollo.

Los datos se han obtenido de [BiziZaragoza](https://www.zaragoza.es/sede/portal/datos-abiertos/servicio/catalogo/70), en el siguiente [fichero JSON](https://www.zaragoza.es/sede/servicio/urbanismo-infraestructuras/estacion-bicicleta.json).

#### Posibles mejoras

No se ha implementado RecyclerView, GSON y Retrofit.

## Mostrando datos

Se muestra el listado y posterior visualización de elementos individuales de las bibliotecas que forman parte de la Xarxa de Biblioteques (red de bibliotecas) de Barcelona.

Los datos se han obtenido de [datos.gob.es](http://datos.gob.es/es/catalogo/l02000008-bibliotecas-municipales), en el siguiente [fichero JSON](http://do.diba.cat/api/dataset/biblioteques/format/json).

#### Posibles mejoras

Es posible mostrar más información, pues cada elemento en el fichero JSON contiene información como la dirección de la biblioteca, horarios de invierno y verano, dirección completa...