angular.module("hCode", [])
.controller("mainCtrl", function($scope) {
    var isSelectionStarted = false,
        lefttop = [],
        bottomright = [];
    $scope.sampleData = generateSample(5, 5);
    $scope.l = 2;
    $scope.h = 6;
    
    $scope.trackSelection = function(r1, c1) {
        isSelectionStarted = !isSelectionStarted;
        if(isSelectionStarted) {
            lefttop = [r1, c1];
        } else {
            bottomright = [r1, c1];
            for(var i = lefttop[0]; i <= r1; i++) {
                for(var j = lefttop[1]; j <= c1; j++) {
                    $scope.sampleData[i][j].selected = true;
                }
            }
        }
    };
    
    $scope.checkIfCorrect = function(r2, c2) {
        var tcnt = 0, mcnt = 0, isSetValid = false;
        if((r2 - lefttop[0] + 1)*(c2 - lefttop[1] + 1) <= $scope.h) {
            for(var i = lefttop[0]; i <= r2; i++) {
                for(var j = lefttop[1]; j <= c2; j++) {
                    if($scope.sampleData[i][j].val == "T") {
                        tcnt++;
                    } else {
                        mcnt++;
                    }
                    if(tcnt >= $scope.l && mcnt >= $scope.l) {
                        isSetValid = true;
                        break;
                    }
                }
            }
        }
        if(isSetValid) {
            for(var i = lefttop[0]; i <= r2; i++) {
                for(var j = lefttop[1]; j <= c2; j++) {
                    $scope.sampleData[i][j].isValid = true;
                }
            }
        }
    };
});

function generateSample(m, n) {
    var sampleData = new Array(m),
        ingredients = ["T", "M"];
    
    for(var i = 0; i < m; i++) {
        sampleData[i] = new Array(n);
        for(var j = 0; j < n; j++) {
            var index = Math.round(Math.random());
            sampleData[i][j] = {val: ingredients[index]};
        }
    }
    
    return sampleData;
}
