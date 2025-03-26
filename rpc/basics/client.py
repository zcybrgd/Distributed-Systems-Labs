import xmlrpc.client

proxy = xmlrpc.client.ServerProxy("http://127.0.0.1:8000/")
result = proxy.add(5, 3)

print(f"Résultat de l'addition : {result}")
