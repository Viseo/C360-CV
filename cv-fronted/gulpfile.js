var gulp = require('gulp');
var sass = require('gulp-sass');
var sourcemaps = require('gulp-sourcemaps');
var autoprefixer = require('gulp-autoprefixer');

gulp.task('css', function () {
  gulp.src('static/*.scss')
    .pipe(sourcemaps.init())
    .pipe(sass())
    .pipe(sourcemaps.write())
    .pipe(autoprefixer())
    .pipe(gulp.dest('static/'))
});

gulp.task('watch', function () {
  gulp.watch('static/*.scss', ['css']);
});

gulp.task('default', ['static', 'watch']);
