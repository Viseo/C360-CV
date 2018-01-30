var gulp = require('gulp');
var sass = require('gulp-sass');
var sourcemaps = require('gulp-sourcemaps');
var autoprefixer = require('gulp-autoprefixer');

gulp.task('css', function () {
  gulp.src('static/CSS/*.scss')
    .pipe(sourcemaps.init())
    .pipe(sass())
    .pipe(sourcemaps.write())
    .pipe(autoprefixer())
    .pipe(gulp.dest('static/CSS/'))
});

gulp.task('watch', function () {
  gulp.watch('static/CSS/*.scss', ['css']);
});

gulp.task('default', ['static/CSS/', 'watch']);
