# Proyectos relacionados para el taller 1 de Modelado y validacion de Arquitectura para la Especializacion en Arquitectura Empresarial de Software de la Pontificia Universidad Javeriana

# suscriptor
Proyecto para suscribir nuevos operadores y registrarlos en base de datos MySql

Servicios expuestos:

application: 
 doc: 
  - 
   "_xmlns:jersey": "http://jersey.java.net/"
   "_jersey:generatedBy": "Jersey: 2.27.payara-p3 2018-05-22 09:50:54"
  - 
   "_xmlns:jersey": "http://jersey.java.net/"
   "_jersey:hint": "This is simplified WADL with user and core resources only. To get full WADL with extended resources use the query parameter detail. Link: http://localhost:8080/suscriptor/conexion/v1/application.wadl?detail=true"
 grammars: 
  include: 
   doc: 
    _title: Generated
    "_xml:lang": en
   _href: "application.wadl/xsd0.xsd"
 resources: 
  resource: 
   method: 
    - 
   response: 
      representation: 
       _mediaType: "*/*"
     _id: getConexiones
     _name: GET
    - 
   request: 
      representation: 
       "_xmlns:ns2": "http://wadl.dev.java.net/2009/02"
       _xmlns: ""
       _element: conexion
       _mediaType: "application/json"
       __prefix: ns2
     response: 
      representation: 
       _mediaType: "*/*"
     _id: crearConexion
     _name: POST
   resource: 
    - 
   param: 
      "_xmlns:xs": "http://www.w3.org/2001/XMLSchema"
      _name: conexion
      _style: template
      _type: "xs:int"
     method: 
      response: 
       representation: 
        _mediaType: "*/*"
      _id: getConexionPorId
      _name: GET
     _path: "/{conexion}"
    - 
   param: 
      "_xmlns:xs": "http://www.w3.org/2001/XMLSchema"
      _name: codigo
      _style: template
      _type: "xs:string"
     method: 
      response: 
       representation: 
        _mediaType: "*/*"
      _id: getConexionPorCodigo
      _name: GET
     _path: "/codigo/{codigo}"
    - 
   param: 
      "_xmlns:xs": "http://www.w3.org/2001/XMLSchema"
      _name: id
      _style: template
      _type: "xs:int"
     method: 
      response: 
       representation: 
        _mediaType: "*/*"
      _id: eliminarConexion
      _name: DELETE
     _path: "/{id}"
   _path: "/conexiones"
  _base: "http://localhost:8080/suscriptor/conexion/v1/"
 _xmlns: "http://wadl.dev.java.net/2009/02"
