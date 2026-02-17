const express = require("express");
const mongoose = require("mongoose");
const dotenv = require("dotenv");
const cors = require("cors");

dotenv.config();

const app = express();

app.use(cors());
app.use(express.json());

mongoose.connect(process.env.MONGO_URI, {
  family: 4
})
  .then(() => console.log("✅ MongoDB Connected"))
  .catch((err) => console.log("❌ Mongo Error:", err));

app.get("/", (req, res) => {
  res.send("Backend Running Successfully 🚀");
});

app.listen(process.env.PORT, () => {
  console.log("Server running on port " + process.env.PORT);
});
