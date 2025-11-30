from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
import secrets
import bcrypt
import datetime
import os

app = Flask(__name__)
app.config['SQLALCHEMY_DATABASE_URI'] = 'sqlite:///otp.db'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False
db = SQLAlchemy(app)

class OTP(db.Model):
    id = db.Column(db.Integer, primary_key=True)
    email = db.Column(db.String(120), unique=True, nullable=False)
    hashed_code = db.Column(db.String(128), nullable=False)
    created_at = db.Column(db.DateTime, default=datetime.datetime.now)

    def __repr__(self):
        return f'<OTP {self.email}>'

if __name__ == '__main__':
    with app.app_context():
        db.create_all()
    app.run(debug=True)