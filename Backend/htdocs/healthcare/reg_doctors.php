<?php
include 'log_redirect.php';
?>
<link rel="stylesheet" type="text/css" href="css/overlay.css">
<div id="overlay_reg_heart">
    <div id="content">
        <button onclick="off_heart()" style="float:right"><img id="close" src="images/Close_Box_Red.png" title="Close"></button>
        <div id="overlayback_reg">
            <p id="sign">Cardiologist</p>
            <hr>
            <form method="post" action="View_Doctors.php" style="float:left;">
                <button id="button" name="special" value="cardio">View<br>Doctors</button>
            </form>
            <form method="post" action="Register_Doctors.php">
            <button id="button" name="special" value="cardio">Register Doctors</button>
            </form>
            <h6></h6>
        </div>
    </div>
</div>
<script>

    function reg_cardio() {
        document.getElementById("overlay_reg_heart").style.display = "block";
    }

    function off_heart() {
        document.getElementById("overlay_reg_heart").style.display = "none";
    }
</script>
<div id="overlay_reg_brain">
    <div id="content">
        <button onclick="off_brain()" style="float:right"><img id="close" src="images/Close_Box_Red.png" title="Close"></button>
        <div id="overlayback_reg">
            <p id="sign">Neurologist</p>
            <hr>
            <form method="post" action="View_Doctors.php" style="float:left;">
                <button id="button" name="special" value="brain">View<br>Doctors</button>
            </form>
            <form method="post" action="Register_Doctors.php">
                <button id="button" name="special" value="brain">Register Doctors</button>
            </form>
        </div>
    </div>
</div>
<script>

    function reg_brain() {
        document.getElementById("overlay_reg_brain").style.display = "block";
    }

    function off_brain() {
        document.getElementById("overlay_reg_brain").style.display = "none";
    }
</script>
<div id="overlay_reg_kidney">
    <div id="content">
        <button onclick="off_kidney()" style="float:right"><img id="close" src="images/Close_Box_Red.png" title="Close"></button>
        <div id="overlayback_reg">
            <p id="sign">Nephrologist</p>
            <hr>
            <form method="post" action="View_Doctors.php" style="float:left;">
                <button id="button" name="special" value="kidney">View<br>Doctors</button>
            </form>
            <form method="post" action="Register_Doctors.php">
                <button id="button" name="special" value="kidney">Register Doctors</button>
            </form>
        </div>
    </div>
</div>
<script>

    function reg_kidney() {
        document.getElementById("overlay_reg_kidney").style.display = "block";
    }

    function off_kidney() {
        document.getElementById("overlay_reg_kidney").style.display = "none";
    }
</script>
<div id="overlay_reg_lungs">
    <div id="content">
        <button onclick="off_lungs()" style="float:right"><img id="close" src="images/Close_Box_Red.png" title="Close"></button>
        <div id="overlayback_reg">
            <p id="sign">Pulmonogist</p>
            <hr>
            <form method="post" action="View_Doctors.php" style="float:left;">
                <button id="button" name="special" value="lungs">View<br>Doctors</button>
            </form>
            <form method="post" action="Register_Doctors.php">
                <button id="button" name="special" value="lungs">Register Doctors</button>
            </form>
        </div>
    </div>
</div>
<script>

    function reg_lungs() {
        document.getElementById("overlay_reg_lungs").style.display = "block";
    }

    function off_lungs() {
        document.getElementById("overlay_reg_lungs").style.display = "none";
    }
</script>


