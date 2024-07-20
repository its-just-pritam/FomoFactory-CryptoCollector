db = db.getSiblingDB('ffcryptodb');

db.createUser({
  user: "ffuser",
  pwd: "ffpassword",
  roles: [{ role: "readWrite", db: "ffcryptodb" }]
})