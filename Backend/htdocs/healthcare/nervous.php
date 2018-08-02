
<div id="overlay">
    <div id="content">
        <button onclick="off()" style="float:right"><img id="close" src="images/Close_Box_Red.png" title="Close"></button>
        <div id="overlayback">
            <p id="sign">NERVOUS SPECIALIST</p>
            <hr>
            <?php
            $nervous="select * from doc_login where d_specialization='nervous'";
            $exe_nervous=$con->query($nervous);
            $nervous_special=mysqli_fetch_array($exe_nervous);
            $nervous_doc_id=$nervous_special['d_id'];
            $nervous_doc_name=$nervous_special['d_name'];
            $nervous_doc_experience=$nervous_special['d_experience'];
            $nervous_doc_age=$nervous_special['age'];
            $nervous_doc_number=$nervous_special['d_mobile'];
            $nervous_doc_gender=$nervous_special['gender'];
            $nervous_doc_blood=$nervous_special['bloodgroup'];
            $nervous_doc_address=$nervous_special['address'];
            ?>
            <img id="side" src="images/cardiologist.jpg">
            <table>
                <tr>
                    <td>ID</td>
                    <td><?php echo $nervous_doc_id;?></td>
                </tr>
                <tr>
                    <td>Name</td>
                    <td><?php echo $nervous_doc_name;?></td>
                </tr>
                <tr>
                    <td>Experience</td>
                    <td><?php echo $nervous_doc_experience;?></td>
                </tr>
                <tr>
                    <td>Age</td>
                    <td><?php echo $nervous_doc_age;?></td>
                </tr>
                <tr>
                    <td>Mobile Number</td>
                    <td><?php echo $nervous_doc_number;?></td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td><?php echo $nervous_doc_gender;?></td>
                </tr>
                <tr>
                    <td>Blood Group</td>
                    <td><?php echo $nervous_doc_blood;?></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><?php echo $nervous_doc_address;?></td>
                </tr>
            </table>
            <h6></h6>
        </div>
    </div>
</div>
<script>

    function nervous() {
        document.getElementById("overlay").style.display = "block";
    }

    function off() {
        document.getElementById("overlay").style.display = "none";
    }
</script>