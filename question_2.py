import pysftp
import mysql.connector
import csv

cnopts = pysftp.CnOpts()
cnopts.hostkeys = None

files=[]
# Make connection to sFTP, change host, username and password accordingly
with pysftp.Connection('192.168.198.1',username='user1',password='root',cnopts = cnopts) as sftp:
    files = sftp.listdir()
    print(files)
    
    #downolading files to local system
    for file in files:
        local_file= "/home/user1/Desktop/csv_files/"+file
        remote_file="/"+file
        sftp.get(remote_file, local_file)

sftp.close()

#connecting to mysql database
mydb = mysql.connector.connect(
  host="localhost",
  user="user1",
  passwd="root12345",
  database = "mydatabase"
)

mycursor = mydb.cursor()
#command to create table 
mycursor.execute("create table CSV_Data(cell_id varchar(50) not null, Granularity_period varchar(50) not null, result_time varchar(50) not null, object_name varchar(50) not null, call_attempts varchar(50) not null)")


#getting data from csv file and uploading into a table in database
for file in files:
    file_withpath = "/home/user1/Desktop/csv_files/"+file
    with open(file_withpath, mode='r') as csv_file:
        csv_reader = csv.reader(csv_file)
        line_count = 0
        for row in csv_reader:
            if line_count != 0:
                print(row)
                mycursor.execute('INSERT INTO CSV_Data(cell_id, Granularity_period, result_time, object_name, call_attempts) VALUES ("%s","%s","%s","%s","%s")',row)
                mydb.commit()
            line_count += 1